using GameDataLibrary.Models;
using GameLibrary.Repositories;
using Microsoft.AspNetCore.Mvc;
using System.Threading;

namespace GameLibrary.Controllers
{

    /// <summary>
    /// Publisher UI Controller
    /// </summary>
    /// <seealso cref="Microsoft.AspNetCore.Mvc.Controller" />
    public class PublisherController : Controller
    {
        private readonly ILogger<PublisherController> logger;
        private readonly IPublisherRepository repository;

        /// <summary>
        /// Initializes a new instance of the <see cref="PublisherController"/> class.
        /// </summary>
        /// <param name="logger">The logger.</param>
        /// <param name="repository">The repository.</param>
        /// <exception cref="System.ArgumentNullException">repository</exception>
        public PublisherController(ILogger<PublisherController> logger, IPublisherRepository repository)
        {
            this.logger = logger ?? throw new System.ArgumentNullException(nameof(repository));
            this.repository = repository ?? throw new System.ArgumentNullException(nameof(repository));
            logger.LogInformation($"PublisherController started");
        }

        /// <summary>
        /// Indexes the specified cancellation token.
        /// </summary>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns></returns>
        public async Task<IActionResult> Index(CancellationToken cancellationToken = default(CancellationToken))
        {
            logger.LogInformation($"{nameof(Index)}");
            return View(await repository.GetAllAsync(cancellationToken));
        }

        /// <summary>
        /// Details the specified identifier.
        /// </summary>
        /// <param name="id">The identifier.</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns></returns>
        public async Task<IActionResult> Details(int id, CancellationToken cancellationToken = default(CancellationToken))
        {
            logger.LogInformation($"{nameof(Details)}");
            var publisherModel = await repository.GetAsync(id,cancellationToken);
            if (publisherModel == null)
            {
                logger.LogInformation($"{nameof(Details)} of {id} not found");
                return NotFound();
            }

            return View(publisherModel);
        }

        /// <summary>
        /// Creates this instance.
        /// </summary>
        /// <returns></returns>
        public IActionResult Create()
        {
            logger.LogInformation($"{nameof(Create)}");
            return View();
        }

        /// <summary>
        /// Creates the specified publisher model.
        /// </summary>
        /// <param name="publisherModel">The publisher model.</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns></returns>
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,Name")] PublisherModel publisherModel, CancellationToken cancellationToken = default(CancellationToken))
        {
            logger.LogInformation($"{nameof(Create)} Save model");
            if (ModelState.IsValid)
            {
                await repository.AddAsync(publisherModel, cancellationToken);
                return RedirectToAction(nameof(Index));
            }
            else
            {
                logger.LogInformation($"{nameof(Create)} invalid model");
            }
            return View(publisherModel);
        }

        /// <summary>
        /// Edits the specified identifier.
        /// </summary>
        /// <param name="id">The identifier.</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns></returns>
        public async Task<IActionResult> Edit(int id, CancellationToken cancellationToken = default(CancellationToken))
        {
            logger.LogInformation($"{nameof(Edit)}");
            var publisherModel = await repository.GetAsync(id,cancellationToken);
            if (publisherModel == null)
            {
                logger.LogInformation($"{nameof(Edit)} of {id} not found");
                return NotFound();
            }
            return View(publisherModel);
        }

        /// <summary>
        /// Edits the specified identifier.
        /// </summary>
        /// <param name="id">The identifier.</param>
        /// <param name="publisherModel">The publisher model.</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns></returns>
        [HttpPut]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Id,Name")] PublisherModel publisherModel, CancellationToken cancellationToken = default(CancellationToken))
        {
            logger.LogInformation($"{nameof(Edit)} Save");
            if (ModelState.IsValid)
            {
                await repository.UpdateAsync(publisherModel, cancellationToken);
                return RedirectToAction(nameof(Index));
            }
            else
            {
                logger.LogInformation($"{nameof(Edit)} invalid model.");
            }
            return View(publisherModel);
        }

        /// <summary>
        /// Deletes the specified identifier.
        /// </summary>
        /// <param name="id">The identifier.</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns></returns>
        public async Task<IActionResult> Delete(int id, CancellationToken cancellationToken = default(CancellationToken))
        {
            logger.LogInformation($"{nameof(Delete)} {id}");
            var publisherModel = await repository.GetAsync(id, cancellationToken);

            if (publisherModel == null)
            {
                logger.LogInformation($"{nameof(Delete)} {id} not found");
                return NotFound();
            }

            return View(publisherModel);
        }

        /// <summary>
        /// Deletes confirmed.
        /// </summary>
        /// <param name="id">The identifier.</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns></returns>
        [HttpDelete]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id, CancellationToken cancellationToken = default(CancellationToken))
        {
            logger.LogInformation($"{nameof(DeleteConfirmed)} {id}");
            var publisherModel = await repository.GetAsync(id, cancellationToken);
            if (publisherModel != null)
            {
                await repository.DeleteAsync(publisherModel);
            }
            else
            {
                logger.LogInformation($"{nameof(DeleteConfirmed)} {id} not found");
            }
            return RedirectToAction(nameof(Index));
        }

    }
}
