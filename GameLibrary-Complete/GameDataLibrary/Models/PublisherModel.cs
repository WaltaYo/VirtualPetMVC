using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json;

namespace GameDataLibrary.Models
{
    /// <summary>
    /// data Model for publisher
    /// </summary>
    [Table("Publishers")]
    public class PublisherModel
    {
        /// <summary>
        /// Table key value
        /// </summary>
        [Key]
        public int Id { get; set; }

        /// <summary>
        /// Publisher Name
        /// </summary>
        [Required]
        [StringLength(100)]
        public string Name { get; set; } = null!;

        /// <summary>
        /// List of BoardGames published by this publisher
        /// </summary>
        public virtual IEnumerable<BoardGameModel>? BoardGames { get; set; }

        /// <summary>
        /// This class as json data
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            return JsonSerializer.Serialize<PublisherModel>(this);
        }
    }
}
