using GameDataLibrary.Models;

namespace GameService.Services
{
    /// <summary>
    /// BoardGame Repository Interface
    /// This is mainly use for dependence Injection
    /// </summary>
    public interface IBoardGameRepository : IService<BoardGameModel>
    {
        /// <summary>
        /// Returns a list of Publishers
        /// </summary>
        /// <returns>List of Publishers</returns>
        ValueTask<IEnumerable<PublisherModel>> GetPublishers();
    }
}