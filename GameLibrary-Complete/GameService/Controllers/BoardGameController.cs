using GameDataLibrary.Models;
using GameService.Services;
using Microsoft.AspNetCore.Mvc;

namespace GameService.Controllers
{
    /// <summary>
    /// Api Controller for the publisher Model
    /// </summary>
    /// <seealso cref="Microsoft.AspNetCore.Mvc.ControllerBase" />
    [Route("api/[controller]")]
    [ApiController]
    public class BoardGameController : ControllerBase
    {
        private readonly ILogger<BoardGameController> logger;
        private readonly IBoardGameService service;
        public BoardGameController(ILogger<BoardGameController> logger, IBoardGameService service)
        {
            this.logger = logger ?? throw new System.ArgumentNullException(nameof(service));
            this.service = service ?? throw new System.ArgumentNullException(nameof(service));
            logger.LogInformation($"BoardGameController started");
        }

        /// <summary>
        /// Gets the board games.
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public async Task<ActionResult<IEnumerable<BoardGameModel>>> GetBoardGames()
        {
            logger.LogInformation($"{nameof(GetBoardGames)}");
            return await service.GetAllAsync();
        }

        /// <summary>
        /// Gets the board game model.
        /// </summary>
        /// <param name="id">The identifier.</param>
        /// <returns></returns>
        [HttpGet("{id}")]
        public async Task<ActionResult<BoardGameModel>> GetBoardGameModel(int id)
        {
            logger.LogInformation($"{nameof(GetBoardGameModel)}");
            var BoardGameModel = await service.GetAsync(id);
            if (BoardGameModel == null)
            {
                logger.LogInformation($"{nameof(GetBoardGameModel)} of {id} not found");
                return NotFound();
            }

            return BoardGameModel;
        }


        /// <summary>
        /// Puts the board game model.
        /// </summary>
        /// <param name="boardGameModel">The board game model.</param>
        /// <returns></returns>
        [HttpPost("Edit")]
        public async Task<ActionResult<BoardGameModel>> PutBoardGameModel([FromBody] BoardGameModel boardGameModel)
        {
            logger.LogInformation($"{nameof(PutBoardGameModel)} Save");
            try
            {
                await service.UpdateAsync(boardGameModel);

            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"{nameof(PutBoardGameModel)} {boardGameModel} ");
                return BadRequest($"{nameof(PutBoardGameModel)} {boardGameModel}");
            }
            return boardGameModel;
        }


        /// <summary>
        /// Posts the board game model.
        /// </summary>
        /// <param name="boardGameModel">The board game model.</param>
        /// <returns></returns>
        [HttpPost]
        public async Task<ActionResult<BoardGameModel>> PostBoardGameModel(BoardGameModel boardGameModel)
        {
            logger.LogInformation($"{nameof(PostBoardGameModel)} Save model");
            try
            {
                await service.AddAsync(boardGameModel);

            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"{nameof(PostBoardGameModel)} {boardGameModel} ");
                return BadRequest($"{nameof(PostBoardGameModel)} {boardGameModel}");
            }
            return boardGameModel;
        }

        /// <summary>
        /// Deletes the board game model.
        /// </summary>
        /// <param name="id">The identifier.</param>
        /// <returns></returns>
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteBoardGameModel(int id)
        {
            logger.LogInformation($"{nameof(DeleteBoardGameModel)} {id}");
            try
            {
                var boardGameModel = await service.GetAsync(id);
                if (boardGameModel != null)
                {
                    await service.DeleteAsync(boardGameModel);
                }
                else
                {
                    logger.LogInformation($"{nameof(DeleteBoardGameModel)} {id} not found");
                    return BadRequest($"{nameof(DeleteBoardGameModel)} {id} not found");
                }
            }
            catch (Exception ex)
            {
                logger.LogError(ex, $"{nameof(DeleteBoardGameModel)} {id}");
                return BadRequest($"{nameof(DeleteBoardGameModel)} {id}");
            }
            return Ok();
        }

    }
}

