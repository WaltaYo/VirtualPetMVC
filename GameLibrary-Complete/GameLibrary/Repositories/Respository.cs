using System.Reflection;
using System.Text;
using System.Text.Json;


namespace GameLibrary.Repositories
{
    /// <summary>
    /// Generic Class to handle Server Request for data
    /// </summary>
    /// <typeparam name="TEntity">The type of the entity.</typeparam>
    /// <seealso cref="GameLibrary.Repositories.IRepository&lt;TEntity&gt;" />
    public class Repository<TEntity> : IRepository<TEntity> where TEntity : class
    {
        protected readonly ILogger<Repository<TEntity>> logger;
        private readonly HttpClient httpClient;
        protected readonly string className;

        /// <summary>
        /// Initializes a new instance of the <see cref="Repository{TEntity}"/> class.
        /// </summary>
        /// <param name="logger">The logger.</param>
        /// <param name="httpClient">The HTTP client.</param>
        /// <exception cref="System.ArgumentNullException">
        /// logger
        /// or
        /// httpClient
        /// </exception>
        public Repository(ILogger<Repository<TEntity>> logger, HttpClient httpClient)
        {
            this.logger = logger ?? throw new System.ArgumentNullException(nameof(logger));
            this.httpClient = httpClient ?? throw new System.ArgumentNullException(nameof(httpClient));

            className = typeof(TEntity).FullName ?? "TEntity";
            logger.LogInformation($"Repository Constructor called for {className}");
        }
        /// <summary>
        /// Add an Entity
        /// </summary>
        /// <param name="entity">Entity Model</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>
        /// Entity Model
        /// </returns>
        public async ValueTask<TEntity> AddAsync(TEntity entity, CancellationToken cancellationToken = default(CancellationToken))
        {
            TEntity? retValue = null;
            try
            {
                var request = JsonSerializer.Serialize(entity);
                var requestContent = new StringContent(request, Encoding.UTF8, "application/json");

                HttpResponseMessage response = await httpClient.PostAsync("", requestContent,cancellationToken);
                retValue = await response.ReadContentAsync<TEntity>(cancellationToken);
            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"Error adding {className} to the Service  {entity}");
                throw;
            }
            return retValue;
        }

        /// <summary>
        /// Delete Entity Model
        /// </summary>
        /// <param name="entity">Entity Model</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>
        /// True if successful
        /// </returns>
        public async ValueTask<bool> DeleteAsync(TEntity entity, CancellationToken cancellationToken = default(CancellationToken))
        {
            bool retValue = false;
            try
            {
                int id = GetId(entity);
                String url = $"{httpClient.BaseAddress}/{id}";
                HttpResponseMessage response = await httpClient.DeleteAsync($"{url}");
                response.EnsureSuccessStatusCode();
                retValue = true;
            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"Error deleting {className} from the Service  {entity}");
                throw;
            }
            return retValue;
        }

        /// <summary>
        /// Gets all TEntity from the API Service
        /// </summary>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>a list od TEntity</returns>
        public async ValueTask<List<TEntity>> GetAllAsync(CancellationToken cancellationToken = default(CancellationToken)) 
        {
            List<TEntity> retValue = new List<TEntity>();
            try
            {
                HttpResponseMessage? response = await httpClient.GetAsync($"");
                retValue = await response.ReadContentAsync<List<TEntity>>();
            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"Error getting all {className} from the Service");
                throw;
            }

            return retValue;
        }
        /// <summary>
        /// Gets a single Entity Model
        /// </summary>
        /// <param name="id">id key</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>
        /// Entity Model
        /// </returns>
        public async ValueTask<TEntity?> GetAsync(int id, CancellationToken cancellationToken = default(CancellationToken))
        {
            TEntity? retValue = null;
            try
            {
                String url = $"{httpClient.BaseAddress}/{id}";
                HttpResponseMessage response = await httpClient.GetAsync(url);
                retValue = await response.ReadContentAsync<TEntity>();
            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"Error getting {className} from the Service with id = {id}");
                throw;
            }
            return retValue;

        }
        /// <summary>
        /// Updates an Entity Model
        /// </summary>
        /// <param name="entity">Entity Model</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>
        /// Entity Model
        /// </returns>
        /// <exception cref="System.NotImplementedException"></exception>
        public async ValueTask<TEntity> UpdateAsync(TEntity entity, CancellationToken cancellationToken = default(CancellationToken))
        {
            TEntity? retValue = null;
            try
            {
                var request = JsonSerializer.Serialize(entity);
                var requestContent = new StringContent(request, Encoding.UTF8, "application/json");

                HttpResponseMessage response = await httpClient.PutAsync("", requestContent);
                retValue = await response.ReadContentAsync<TEntity>();
            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"Error adding {className} to the Service  {entity}");
                throw;
            }
            return retValue;
        }

        /// <summary>
        /// Gets the id value from generic type
        /// </summary>
        /// <param name="entity">generic type</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>id value</returns>
        private int GetId(TEntity entity, CancellationToken cancellationToken = default(CancellationToken))
        {
            int id = 0;
            Type t = entity.GetType();

            PropertyInfo? prop = t.GetProperty("Id");
            if (prop is not null)
            {
                object? value = prop.GetValue(entity);
                if (value is not null)
                {
                    id = (int)value;
                }
            }
            return id;
        }
    }
}

