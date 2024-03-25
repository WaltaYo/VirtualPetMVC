using GameDataLibrary.Models;

namespace GameLibrary.Repositories
{
    /// <summary>
    /// Publisher Repository Interface
    /// This is mainly use for dependence Injection
    /// </summary>
    public interface IPublisherRepository : IRepository<PublisherModel>
    { }
}