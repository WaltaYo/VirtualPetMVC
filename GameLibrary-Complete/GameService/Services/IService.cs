namespace GameService.Services
{
    /// <summary>
    /// Repository interface that is used both on the entity framework side and Web API 
    /// </summary>
    /// <typeparam name="TEntity">Entity Model</typeparam>
    public interface IService<TEntity> where TEntity : class
    {
        /// <summary>
        /// Add an Entity 
        /// </summary>
        /// <param name="entity">Entity Model</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>Entity Model</returns>
        ValueTask<TEntity> AddAsync(TEntity entity, CancellationToken cancellationToken = default(CancellationToken));
        /// <summary>
        /// Delete Entity Model
        /// </summary>
        /// <param name="entity">Entity Model</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>True if successful</returns>
        ValueTask<bool> DeleteAsync(TEntity entity, CancellationToken cancellationToken = default(CancellationToken));
        /// <summary>
        /// Gets all the Entity Model
        /// </summary>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>List<Entity></Entity> Model</returns>
        ValueTask<List<TEntity>> GetAllAsync(CancellationToken cancellationToken = default(CancellationToken));
        /// <summary>
        /// Gets a single Entity Model
        /// </summary>
        /// <param name="id">id key</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>Entity Model</returns>
        ValueTask<TEntity?> GetAsync(int id, CancellationToken cancellationToken = default(CancellationToken));
        /// <summary>
        /// Updates an Entity Model
        /// </summary>
        /// <param name="entity">Entity Model</param>
        /// <param name="cancellationToken">The cancellation token.</param>
        /// <returns>Entity Model</returns>
        ValueTask<TEntity> UpdateAsync(TEntity entity, CancellationToken cancellationToken = default(CancellationToken));
    }
}