using GameDataLibrary.Models;
using GameService.Services;
using Microsoft.AspNetCore.Mvc;

namespace GameService.Controllers
{
    /// <summary>
    /// Controller for Publisher Model
    /// </summary>
    /// <seealso cref="Microsoft.AspNetCore.Mvc.ControllerBase" />
    [Route("api/[controller]")]
    [ApiController]
    public class PublisherController : ControllerBase
    {
        private readonly ILogger<PublisherController> logger;
        private readonly IPublisherService service;
        public PublisherController(ILogger<PublisherController> logger, IPublisherService service)
        {
            this.logger = logger ?? throw new System.ArgumentNullException(nameof(service));
            this.service = service ?? throw new System.ArgumentNullException(nameof(service));
            logger.LogInformation($"PublisherController started");
        }

        /// <summary>
        /// Gets the publishers.
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public async Task<ActionResult<IEnumerable<PublisherModel>>> GetPublishers()
        {
            logger.LogInformation($"{nameof(GetPublishers)}");
            return await service.GetAllAsync();
        }

        /// <summary>
        /// Gets the publisher model.
        /// </summary>
        /// <param name="id">The identifier.</param>
        /// <returns></returns>
        [HttpGet("{id}")]
        public async Task<ActionResult<PublisherModel>> GetPublisherModel(int id)
        {
            logger.LogInformation($"{nameof(GetPublisherModel)}");
            var publisherModel = await service.GetAsync(id);
            if (publisherModel == null)
            {
                logger.LogInformation($"{nameof(GetPublisherModel)} of {id} not found");
                return NotFound();
            }

            return publisherModel;
        }


        /// <summary>
        /// Puts the publisher model.
        /// </summary>
        /// <param name="publisherModel">The publisher model.</param>
        /// <returns></returns>
        [HttpPut]
        public async Task<ActionResult<PublisherModel>> PutPublisherModel([FromBody] PublisherModel publisherModel)
        {
            logger.LogInformation($"{nameof(PutPublisherModel)} Save");
            try
            {
                await service.UpdateAsync(publisherModel);

            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"{nameof(PutPublisherModel)} {publisherModel} ");
                return BadRequest($"{nameof(PutPublisherModel)} {publisherModel}");
            }
            return publisherModel;
        }


        /// <summary>
        /// Posts the publisher model.
        /// </summary>
        /// <param name="publisherModel">The publisher model.</param>
        /// <returns></returns>
        [HttpPost]
        public async Task<ActionResult<PublisherModel>> PostPublisherModel(PublisherModel publisherModel)
        {
            logger.LogInformation($"{nameof(PostPublisherModel)} Save model");
            try
            {
                await service.AddAsync(publisherModel);

            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"{nameof(PostPublisherModel)} {publisherModel} ");
                return BadRequest($"{nameof(PostPublisherModel)} {publisherModel}");
            }
            return publisherModel;
        }

        /// <summary>
        /// Deletes the publisher model.
        /// </summary>
        /// <param name="id">The identifier.</param>
        /// <returns></returns>
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeletePublisherModel(int id)
        {
            logger.LogInformation($"{nameof(DeletePublisherModel)} {id}");
            try
            {
                var publisherModel = await service.GetAsync(id);
                if (publisherModel != null)
                {
                    await service.DeleteAsync(publisherModel);
                }
                else
                {
                    logger.LogInformation($"{nameof(DeletePublisherModel)} {id} not found");
                    return BadRequest($"{nameof(DeletePublisherModel)} {id} not found");
                }
            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"{nameof(DeletePublisherModel)} {id}");
                return BadRequest($"{nameof(DeletePublisherModel)} {id}");
            }
            return Ok();
        }

    }
}
