using GameService.Context;
using GameService.Services;
namespace GameService
{
    /// <summary>
    /// Static Extension class used to add Dependence Injection of local class's
    /// </summary>
    public static class Startup
    {
        /// <summary>
        /// Database injection extension service
        /// </summary>
        /// <param name="service">ServiceCollection</param>
        /// <returns>ServiceCollection</returns>
        public static IServiceCollection AddDbService(this IServiceCollection service)
        {
            service.AddDbContext<GameDataContext>();
            service.AddScoped<IPublisherService, PublisherService>();
            service.AddScoped<IBoardGameService, BoardGameService>();
            return service;
        }
      
    }
}
