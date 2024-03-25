using GameDataLibrary.Models;

namespace GameLibrary.Repositories
{
    /// <summary>
    /// BoardGame Repository Interface
    /// This is mainly use for dependence Injection
    /// </summary>
    public interface IBoardGameRepository : IRepository<BoardGameModel>
    {
        /// <summary>
        /// Returns a list of Publishers
        /// </summary>
        /// <returns>List of Publishers</returns>
        ValueTask<IEnumerable<PublisherModel>> GetPublishers(CancellationToken cancellationToken = default(CancellationToken));
    }
}