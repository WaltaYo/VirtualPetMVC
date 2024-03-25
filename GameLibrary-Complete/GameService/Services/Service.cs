using GameService.Context;
using Microsoft.EntityFrameworkCore;


namespace GameService.Services
{
    /// <summary>
    /// Generic Service to Handle default EntityFrame work Database Tables.
    /// </summary>
    /// <remarks>
    /// This Service only returns detached/NoTracking entities to speed up processing and cut back on network.
    /// https://www.c-sharpcorner.com/UploadFile/ff2f08/entity-framework-and-asnotracking/
    /// </remarks>
    /// <typeparam name="TEntity">Data Model.</typeparam>
    public abstract class Service<TEntity> : IService<TEntity> where TEntity : class
    {
        protected readonly ILogger<Service<TEntity>> logger;
        protected readonly GameDataContext dbContext;
        protected readonly string className;

        /// <summary>
        /// Constructor 
        /// </summary>
        /// <param name="logger">Injected logger</param>
        /// <param name="dbContext">injected DbContext</param>
        /// <exception cref="System.ArgumentNullException"></exception>
        public Service(ILogger<Service<TEntity>> logger, GameDataContext dbContext)
        {
            this.logger = logger ?? throw new System.ArgumentNullException(nameof(logger));
            this.dbContext = dbContext ?? throw new System.ArgumentNullException(nameof(dbContext));

            className = typeof(TEntity).FullName ?? "TEntity";
            logger.LogInformation($"Repository Constructor called for {className}");
        }

        /// <summary>
        /// Adds the Entity Model to the Database Table
        /// </summary>
        /// <param name="entity">entity model</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>entity model after adding</returns>
        public virtual async ValueTask<TEntity> AddAsync(TEntity entity, CancellationToken cancellationToken = default(CancellationToken))
        {
            try
            {
                dbContext.Set<TEntity>() //dynamically builds a DbSet to use
                    .Add(entity); //Adds the entity to the database
                await dbContext.SaveChangesAsync(cancellationToken); // Saves the entity
                dbContext.Entry(entity).State = EntityState.Detached; //Detaches the entity before returning
            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"Error adding new {className} to the Database {entity}");
                throw;
            }

            return entity;
        }

        /// <summary>
        /// Updates the entity model
        /// </summary>
        /// <param name="entity">entity model to update</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>Entity model after update</returns>
        public virtual async ValueTask<TEntity> UpdateAsync(TEntity entity, CancellationToken cancellationToken = default(CancellationToken))
        {
            try
            {
                dbContext.Set<TEntity>() //dynamically builds a DbSet to use
                    .Add(entity) //Adds the entity to the databases
                    .State = EntityState.Modified; //Sets the state to modified
                await dbContext.SaveChangesAsync(cancellationToken); // Save the changes
                dbContext.Entry(entity).State = EntityState.Detached; //Detaches the entity before returning

                return entity;
            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"Error saving {className} changes to the Database {entity}");
                throw;
            }
           
        }

        /// <summary>
        /// Deletes an entity model from the database
        /// </summary>
        /// <param name="entity">Entity model to delete</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns></returns>
        public async virtual ValueTask<bool> DeleteAsync(TEntity entity, CancellationToken cancellationToken = default(CancellationToken))
        {
            bool retValue = false;
            try
            {
                dbContext.Set<TEntity>() //dynamically builds a DbSet to use
                    .Remove(entity); 
                await dbContext.SaveChangesAsync(cancellationToken);
                retValue = true;
            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"Error deleting {className} from the Database  {entity}");    
            }

            return retValue;
        }

        /// <summary>
        /// Gets an entity from the database base on Id
        /// </summary>
        /// <param name="id">int Id</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>returns the model if found or a Null value</returns>
        public virtual async ValueTask<TEntity?> GetAsync(int id, CancellationToken cancellationToken = default(CancellationToken))
        {
            TEntity? retValue;
            try
            {
                IQueryable<TEntity> query = dbContext.Set<TEntity>() //dynamically builds a DbSet to use
                    .Where(w => EF.Property<int>(w, "Id") == id) //dynamically uses the id in the where clause
                    .AsNoTracking(); // removes the tracking object
                // https://docs.google.com/document/d/1KCb19tJKFT6ROh9c1yl4iNC7nw1wVSslGTe8sXyP1yg
                retValue = await query.FirstOrDefaultAsync(cancellationToken); // Gets a single entity model
            }
            catch (Exception ex)
            {

                logger.LogError(ex, $"Error getting {className} from the Database using Id = {id}");
                throw;
            }

            return retValue;
        }
        /// <summary>
        /// Gets the entire table from the database
        /// </summary>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>a list of entities</returns>
        public virtual async ValueTask<List<TEntity>> GetAllAsync(CancellationToken cancellationToken = default(CancellationToken))
        {
            try
            {
                return await dbContext.Set<TEntity>() //dynamically builds a DbSet to use
                    .AsNoTracking() // removes the tracking object
                    .ToListAsync(cancellationToken); //return a list of entities

            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"Error getting all {className} from the Database");
                throw;
            }
        }
    }
}
