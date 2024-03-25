
using GameDataLibrary.Models;
using GameService.Context;
using Microsoft.EntityFrameworkCore;


namespace GameService.Services
{
    /// <summary>
    /// Database repository for table Publishers
    /// </summary>
    /// <remarks>inherits the RepositoryDB and  IPublisherRepository</remarks>
    public class PublisherService : Service<PublisherModel>, IPublisherService
    {
        /// <summary>
        /// Constructor 
        /// </summary>
        /// <param name="logger">Injected logger</param>
        /// <param name="dbContext">injected DbContext</param>
        /// <remarks>the base constructor does a Null argument exception check</remarks>
        public PublisherService(ILogger<PublisherService> logger, GameDataContext dbContext)
            : base(logger, dbContext) { }

       /// <summary>
       /// We over ride this method so we can include the related BoardGame entities associated with this publisher
       /// </summary>
       /// <param name="id">Id of the publisher</param>
       /// <returns>a publisher with it's board games</returns>
        public override async ValueTask<PublisherModel?> GetAsync(int id, CancellationToken cancellationToken = default(CancellationToken))
        {
            PublisherModel? retValue = null;
            try
            {
                retValue = await dbContext.Set<PublisherModel>() //dynamically builds a DbSet to use
                    .Where(w => EF.Property<int>(w, "Id") == id) //dynamically uses the id in the where clause
                    .Include(b => b.BoardGames) // Includes Board Games
                    .AsNoTracking()  // removes the tracking object
                    .FirstOrDefaultAsync(); // Gets a single entity model
            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"Error getting all {className} from the Database");
                throw;
            }
            return retValue;
        }
    }
}
