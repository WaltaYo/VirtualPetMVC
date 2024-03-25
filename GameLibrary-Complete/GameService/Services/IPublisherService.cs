using GameDataLibrary.Models;

namespace GameService.Services
{
    /// <summary>
    /// Publisher Repository Interface
    /// This is mainly use for dependence Injection
    /// </summary>
    public interface IPublisherService : IService<PublisherModel>
    { }
}