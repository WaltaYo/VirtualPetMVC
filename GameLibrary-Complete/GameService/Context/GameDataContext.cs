using GameDataLibrary.Models;
using Microsoft.EntityFrameworkCore;

namespace GameService.Context
{
    /// <summary>
    /// Handles the Database Connection
    /// </summary>
    public class GameDataContext : DbContext
    {

        private readonly IConfiguration configuration;


        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="configuration">DI IConfiguration</param>
        /// <exception cref="ArgumentNullException">if the configuration is not in the DI register</exception>
        public GameDataContext(IConfiguration configuration)
        {
            this.configuration = configuration ?? throw new ArgumentNullException(paramName: nameof(configuration), message: "IConfiguration is not defined");
        }

        /// <summary>
        /// Gets or sets the board games.
        /// </summary>
        /// <value>
        /// The board games.
        /// </value>
        public DbSet<BoardGameModel> BoardGames { get; set; }

        /// <summary>
        /// Gets or sets the publishers.
        /// </summary>
        /// <value>
        /// The publishers.
        /// </value>
        public DbSet<PublisherModel> Publishers { get; set; }

        /// <summary>
        /// Override this method to configure the database (and other options) to be used for this context.
        /// This method is called for each instance of the context that is created.
        /// The base implementation does nothing.
        /// </summary>
        /// <param name="optionsBuilder">A builder used to create or modify options for this context. Databases (and other extensions)
        /// typically define extension methods on this object that allow you to configure the context.</param>
        /// <exception cref="System.InvalidOperationException">can not find Connection string DefaultConnection</exception>
        /// <remarks>
        /// <para>
        /// In situations where an instance of <see cref="T:Microsoft.EntityFrameworkCore.DbContextOptions" /> may or may not have been passed
        /// to the constructor, you can use <see cref="P:Microsoft.EntityFrameworkCore.DbContextOptionsBuilder.IsConfigured" /> to determine if
        /// the options have already been set, and skip some or all of the logic in
        /// <see cref="M:Microsoft.EntityFrameworkCore.DbContext.OnConfiguring(Microsoft.EntityFrameworkCore.DbContextOptionsBuilder)" />.
        /// </para>
        /// <para>
        /// See <see href="https://aka.ms/efcore-docs-dbcontext">DbContext lifetime, configuration, and initialization</see>
        /// for more information and examples.
        /// </para>
        /// </remarks>
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            string? connectionString = configuration.GetConnectionString("DefaultConnection");
            if (connectionString != null)
            {
                optionsBuilder.UseSqlServer(connectionString);
            }
            else
            {
                throw new InvalidOperationException("can not find Connection string DefaultConnection");
            }
        }

        /// <summary>
        /// Override this method to further configure the model that was discovered by convention from the entity types
        /// exposed in <see cref="T:Microsoft.EntityFrameworkCore.DbSet`1" /> properties on your derived context. The resulting model may be cached
        /// and re-used for subsequent instances of your derived context.
        /// </summary>
        /// <param name="modelBuilder">The builder being used to construct the model for this context. Databases (and other extensions) typically
        /// define extension methods on this object that allow you to configure aspects of the model that are specific
        /// to a given database.</param>
        /// <remarks>
        /// <para>
        /// If a model is explicitly set on the options for this context (via <see cref="M:Microsoft.EntityFrameworkCore.DbContextOptionsBuilder.UseModel(Microsoft.EntityFrameworkCore.Metadata.IModel)" />)
        /// then this method will not be run. However, it will still run when creating a compiled model.
        /// </para>
        /// <para>
        /// See <see href="https://aka.ms/efcore-docs-modeling">Modeling entity types and relationships</see> for more information and
        /// examples.
        /// </para>
        /// </remarks>
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // Add all publishers seed data first
            modelBuilder.Entity<PublisherModel>().HasData(
                new PublisherModel()
                {
                    Id = 1,
                    Name = "Days of Wonder"
                },
                new PublisherModel()
                {
                    Id = 2,
                    Name = "Stonemaier Games"
                },
                  new PublisherModel()
                  {
                      Id = 3,
                      Name = "Hasbro"
                  },
                  new PublisherModel()
                  {
                      Id = 4,
                      Name = "Spin Master"
                  }
                );

            // Add BoardGame seed data
            modelBuilder.Entity<BoardGameModel>().HasData(
                new BoardGameModel()
                {
                    Id = 1,
                    PublishersId = 1,
                    Name = "SmallWorld",
                    Description = "Control one fantasy race after another to expand throught the land",
                    ImageURL = "https://cf.geekdo-images.com/aoPM07XzoceB-RydLh08zA__imagepage/img/lHmv0ddOrUvpiLcPeQbZdT5yCEA=/fit-in/900x600/filters:no_upscale():strip_icc()/pic428828.jpg"
                },
                new BoardGameModel()
                {
                    Id = 2,
                    PublishersId = 2,
                    Name = "WingSpan",
                    Description = "Attract a beautiful and diverse" +
                    "collection of birds to your wildlife preserve.",
                    ImageURL = "https://cf.geekdo-images.com/yLZJCVLlIx4c7eJEWUNJ7w__imagepagezoom/img/yS4vL6iTCvHSvGySxyOjV_-R3dI=/fit-in/1200x900/filters:no_upscale():strip_icc()/pic4458123.jpg"
                },
                new BoardGameModel()
                {
                    Id = 3,
                    PublishersId = 3,
                    Name = "Trouble",
                    Description = "Hasbro Gaming Trouble Board Game for Kids Ages 5 and Up 2-4 Players",
                    ImageURL = "https://m.media-amazon.com/images/I/81MdgnO4l9L._AC_UL400_.jpg"
                },
                new BoardGameModel()
                {
                    Id = 4,
                    PublishersId = 3,
                    Name = "The Game of Life",
                    Description = "Hasbro Gaming The Game of Life Board Game Ages 8 & Up",
                    ImageURL = "https://m.media-amazon.com/images/I/81yQxkx3vwL._AC_UL640_QL65_.jpg"
                },
                new BoardGameModel()
                {
                    Id = 5,
                    PublishersId = 3,
                    Name = "Candy Land",
                    Description = "Hasbro Gaming Candy Land Kingdom Of Sweet Adventures Board Game For Kids Ages",
                    ImageURL = "https://m.media-amazon.com/images/I/91yUG40gv0L._AC_UL400_.jpg"
                }
                ,
                new BoardGameModel()
                {
                    Id = 6,
                    PublishersId = 3,
                    Name = "Risk",
                    Description = "Hasbro Gaming Risk Military Wargame",
                    ImageURL = "https://m.media-amazon.com/images/I/91jsvpbPP3L._AC_UL400_.jpg"
                },
                new BoardGameModel()
                {
                    Id = 7,
                    PublishersId = 1,
                    Name = "Ticket to ride",
                    Description = "Ticket to Ride Board Game | Family Board Game | Board Game for Adults and Family",
                    ImageURL = "https://m.media-amazon.com/images/I/91YNJM4oyhL._AC_UL400_.jpg"
                },
                new BoardGameModel()
                {
                    Id = 8,
                    PublishersId = 4,
                    Name = "Sorry",
                    Description = "ORRY Classic Family Board Game Indoor Outdoor Retro Party Activity Summer Toy with Oversized Gameboard",
                    ImageURL = "https://m.media-amazon.com/images/I/81ItkRyOaaL._AC_UL400_.jpg"
                }
                );
        }

    }

}
