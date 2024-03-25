using GameDataLibrary.Models;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace GameLibrary.Repositories
{
    /// <summary>
    /// Repository for BoardGames
    /// </summary>
    /// <seealso cref="GameLibrary.Repositories.Repository&lt;GameDataLibrary.Models.BoardGameModel&gt;" />
    /// <seealso cref="GameLibrary.Repositories.IBoardGameRepository" />
    public class BoardGameRepository : Repository<BoardGameModel>, IBoardGameRepository
    {
       private IPublisherRepository publisher;
        public BoardGameRepository(ILogger<Repository<BoardGameModel>> logger, HttpClient httpClient, IPublisherRepository publisher) 
            : base(logger, httpClient)
        {
            this.publisher = publisher ?? throw new System.ArgumentNullException(nameof(publisher)); ;
        }

        /// <summary>
        /// Returns a list of Publishers
        /// </summary>
        /// <param name="cancellationToken"></param>
        /// <returns>
        /// List of Publishers
        /// </returns>
        public async ValueTask<IEnumerable<PublisherModel>> GetPublishers(CancellationToken cancellationToken = default(CancellationToken))
        {
            return await publisher.GetAllAsync(cancellationToken);
        }
    }
}
