using GameDataLibrary.Models;

namespace GameLibrary.Repositories
{
    public class PublisherRepository : Repository<PublisherModel>, IPublisherRepository
    {
        public PublisherRepository(ILogger<Repository<PublisherModel>> logger, HttpClient httpClient) : base(logger, httpClient)
        {
        }
    }
}
