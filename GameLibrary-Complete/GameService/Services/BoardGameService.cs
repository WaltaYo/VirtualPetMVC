using GameDataLibrary.Models;
using GameService.Context;
using Microsoft.EntityFrameworkCore;


namespace GameService.Services
{
    /// <summary>
    /// Database repository for table BoardGame
    /// </summary>
    /// <remarks>inherits the RepositoryDB and  IBoardGameRepository</remarks>
    public class BoardGameService : Service<BoardGameModel>, IBoardGameService
    {

        /// <summary>
        /// Constructor 
        /// </summary>
        /// <param name="logger">Injected logger</param>
        /// <param name="dbContext">injected DbContext</param>
        /// <remarks>the base constructor does a Null argument exception check</remarks>
        public BoardGameService(ILogger<BoardGameService> logger, GameDataContext dbContext)
            : base(logger, dbContext) { }

        /// <summary>
        /// We over ride this method so we can include the related BoardGame entities associated with this publisher
        /// </summary>
        /// <param name="id">Id of the publisher</param>
        /// <returns>a publisher with it's board games</returns>
        public override async ValueTask<BoardGameModel?> GetAsync(int id, CancellationToken cancellationToken = default(CancellationToken))
        {
            BoardGameModel? retValue = null;
            try
            {
                retValue = await dbContext.Set<BoardGameModel>()
                    .Where(w => EF.Property<int>(w, "Id") == id)
                    .Include(p => p.Publishers)
                    .AsNoTracking()
                    .FirstOrDefaultAsync();
            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"Error getting {className} from the Database using Id = {id}");
                throw;
            }
            return retValue;
        }
        /// <summary>
        /// We over ride this method so we can include the related BoardGame entities associated with this publisher
        /// </summary>
        /// <param name="id">Id of the publisher</param>
        /// <returns>a publisher with it's board games</returns>
        public override async ValueTask<List<BoardGameModel>> GetAllAsync(CancellationToken cancellationToken = default(CancellationToken))
        {
            try
            {

                return await dbContext.Set<BoardGameModel>()
                    .Include(p => p.Publishers)
                    .AsNoTracking()
                    .ToListAsync();

            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"Error getting all {className} from the Database");
                throw;
            }
        }
        /// <summary>
        /// This method gets the publishers 
        /// </summary>
        /// <returns>a List publisher</returns>
        public async ValueTask<IEnumerable<PublisherModel>> GetPublishers()
        {
            try
            {

                return await dbContext.Set<PublisherModel>()
                    .AsNoTracking()
                    .OrderBy(p => p.Name)
                    .ToListAsync();

            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"Error getting all {className} from the Database");
                throw;
            }
        }
    }
}
