using GameDataLibrary.Models;
using GameLibrary.Repositories;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Threading;

namespace GameLibrary.Controllers
{
    /// <summary>
    /// Controller to handle the UI for the Board Game
    /// </summary>
    /// <seealso cref="Microsoft.AspNetCore.Mvc.Controller" />
    public class BoardGameController : Controller
    {
        private readonly ILogger<BoardGameController> logger;
        private readonly IBoardGameRepository repository;

        /// <summary>
        /// Initializes a new instance of the <see cref="BoardGameController"/> class.
        /// </summary>
        /// <param name="logger">The logger.</param>
        /// <param name="repository">The repository.</param>
        /// <exception cref="System.ArgumentNullException">
        /// logger
        /// or
        /// repository
        /// </exception>
        public BoardGameController(ILogger<BoardGameController> logger, IBoardGameRepository repository)
        {
            this.logger = logger ?? throw new System.ArgumentNullException(nameof(logger));
            this.repository = repository ?? throw new System.ArgumentNullException(nameof(repository));
            logger.LogInformation($"BoardGameController started");
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
        /// Details for the specified identifier.
        /// </summary>
        /// <param name="id">The identifier.</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns></returns>
        public async Task<IActionResult> Details(int id, CancellationToken cancellationToken = default(CancellationToken))
        {

            logger.LogInformation($"{nameof(Details)} of {id}");
            var boardGameModel = await repository.GetAsync(id, cancellationToken);

            if (boardGameModel == null)
            {
                logger.LogInformation($"{nameof(Details)} of {id} not found");
                return NotFound();
            }

            return View(boardGameModel);
        }

        /// <summary>
        /// Creates the specified cancellation token.
        /// </summary>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns></returns>
        public async Task<IActionResult> Create(CancellationToken cancellationToken = default(CancellationToken))
        {
            logger.LogInformation($"{nameof(Create)}");
            ViewData["PublishersId"] = new SelectList(await repository.GetPublishers(cancellationToken), "Id", "Name");
            return View();
        }

        /// <summary>
        /// Creates the specified board game model.
        /// </summary>
        /// <param name="boardGameModel">The board game model.</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns></returns>
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,Name,Description,PublishersId,ImageURL")] BoardGameModel boardGameModel, CancellationToken cancellationToken = default(CancellationToken))
        {
            logger.LogInformation($"{nameof(Create)} Save model");
            if (ModelState.IsValid)
            {
                await repository.AddAsync(boardGameModel, cancellationToken);
                logger.LogInformation($"{nameof(Create)} new BoardGame {boardGameModel}");
                return RedirectToAction(nameof(Index));
            }
            else
            {
                logger.LogInformation($"{nameof(Create)} invalid model");
            }
            ViewData["PublishersId"] = new SelectList(await repository.GetPublishers(cancellationToken), "Id", "Name");
            return View(boardGameModel);
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
            var boardGameModel = await repository.GetAsync(id, cancellationToken);
            if (boardGameModel == null)
            {
                logger.LogInformation($"{nameof(Edit)} BoardGameModel not found");
                return NotFound();
            }
            ViewData["PublishersId"] = new SelectList(await repository.GetPublishers(cancellationToken), "Id", "Name");
            return View(boardGameModel);
        }

        /// <summary>
        /// Edits the specified identifier.
        /// </summary>
        /// <param name="id">The identifier.</param>
        /// <param name="boardGameModel">The board game model.</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns></returns>
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Id,Name,Description,PublishersId,ImageURL")] BoardGameModel boardGameModel, CancellationToken cancellationToken = default(CancellationToken))
        {
            logger.LogInformation($"{nameof(Edit)} Save");
            if (ModelState.IsValid)
            {
                await repository.UpdateAsync(boardGameModel, cancellationToken);
                return RedirectToAction(nameof(Index));
            }
            else
            {
                logger.LogInformation($"{nameof(Edit)} invalid model.");
            }
            ViewData["PublishersId"] = new SelectList(await repository.GetPublishers(cancellationToken), "Id", "Name");
            return View(boardGameModel);
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
            var boardGameModel = await repository.GetAsync(id,cancellationToken);
            if (boardGameModel == null)
            {
                logger.LogInformation($"{nameof(Delete)} BoardGameModel not found");
                return NotFound();
            }
            return View(boardGameModel);
        }

        /// <summary>
        /// Deletes the confirmed.
        /// </summary>
        /// <param name="id">The identifier.</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns></returns>
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id, CancellationToken cancellationToken = default(CancellationToken))
        {
            logger.LogInformation($"{nameof(DeleteConfirmed)} {id}");
            var boardGameModel = await repository.GetAsync(id, cancellationToken);
            if (boardGameModel != null)
            {
                await repository.DeleteAsync(boardGameModel, cancellationToken);
            }
            else
            {
                logger.LogInformation($"{nameof(Delete)} BoardGameModel not found, can not delete");
            }
            return RedirectToAction(nameof(Index));
        }

    }
}
