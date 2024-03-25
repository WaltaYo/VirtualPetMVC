
using GameLibrary.Repositories;

namespace GameLibrary
{
    /// <summary>
    /// Static Extension class used to add Dependence Injection of local class's
    /// </summary>
    public static class Startup
    {
        
        
        public static IServiceCollection AddApiService(this IServiceCollection service, IConfiguration configuration)
        {
            var section = configuration.GetSection("URL");
            String? urlPublisher = section.GetValue("Publisher", @"https://localhost:7086/api/Publisher");
            String? urlBoardGame = section.GetValue("BoardGame", @"https://localhost:7086/api/BoardGame");

            service.AddHttpClient<IPublisherRepository, PublisherRepository>(c =>
            {
                c.BaseAddress = new Uri(urlPublisher);
            });

            service.AddHttpClient<IBoardGameRepository, BoardGameRepository>(c =>
             {
                 c.BaseAddress = new Uri(urlBoardGame);
             });
            return service;
        }
    }
}
