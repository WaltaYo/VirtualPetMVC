<p style="text-align: center"><image src="WECANCodeIT.png" /></p>

# GameSolution

**Table of Contents**

- [GameSolution](#gamesolution)
  - [Overview](#overview)
  - [Skills Required](#skills-required)
  - [Phase 1. Build an MVC Application](#phase-1-build-an-mvc-application)
  - [Phase 2. Data Models](#phase-2-data-models)
  - [Phase 3. Data Context](#phase-3-data-context)
  - [Phase 4. Data Services](#phase-4-data-services)
  - [Phase 5. Dependance Injection](#phase-5-dependance-injection)
  - [Phase 6. Data Migration](#phase-6-data-migration)
  - [Phase 7. Data Repositories](#phase-7-data-repositories)
    - [Phase 8. MVC Controllers in GameLibrary](#phase-8-mvc-controllers-in-gamelibrary)
  - [Step 8. Modifity Views](#step-8-modifity-views)
  - [To set multiple startup projects](#to-set-multiple-startup-projects)

## Overview

 >The purpose of this code along project is to get the student accustom to working with more advanced coding technics. Such as Interfaces, Generics, Repositories, Dependance Injection and Web API's and Entity Framework with in an MVC application. this whole session should take two weeks with some stretch tasks.

## Skills Required

1. multi-Project solution
2. Generic Class
3. Exensions to class
4. Logging
5. Entity Framework
6. MCV Project
7. Web Api project
8. Excepiton handling

## Phase 1. Build an MVC Application

 Step 1 Solution Setup

- Solution Name **GameSolution** (You should download this from the Canvas)
- Project 1 ASP MVC app **GameLibrary**
- Project 2 Class Library **GameDataLibrary**  
- Project 3 Web API **GameService**  

## Phase 2. Data Models

> The Data Models will be used by the UI as well as the Database. We will not be using DTO's in this application. This would not be considered as normal procedure.

1. In the **GameDataLibrary** create a folder **Models**

2. Create class **PublisherModel**

- Table name should be Publishers
- Table Key is Id
- User needs to have a Name
  - Name is Required
  - Name can not be empty
  - Name can not be null
  - Name Can not be longer then 100 characters
- User wants a list of board games the publisher owns
- User Need to convert Class to Json String
  - User wants a Json string of the class
    - Override ToString()

 ```` C#
 public override string ToString()
        {
            return JsonSerializer.Serialize<PublisherModel>(this);
        }
````
  
1. Create Class **BoardGameModel**

- Table name should be BoardGames
- Table Key is Id
- User needs to have a Name
  - Name is Required
  - Name can not be empty
  - Name can not be null
  - Name Can not be longer then 100 characters
- User needs to have a Description
  - Description is Required
  - Description can not be empty
  - Description can not be null
  - Description Can not be longer then 500 characters
- User needs to have a ImageURL
  - ImageURL is Required
  - ImageURL can not be empty
  - ImageURL can not be null
  - ImageURL should be a vaild Url
  - ImageURL Can not be longer then 500 characters
- User needs a reference to the publisher that makes this game
  - PublishersId is required
  - PublishersId is a Foreign Key to the PublisherModel
- User wants a link back to the PublisherModel Data
  - The Publishers property should be Nullable
  - The Publishers property should be virtual
- User needs quicke access to the publishers name
  - Publisher needs to be Not Mapped to the Database
  - Publisher needs to be a read only property
  - Publisher needs to check for a Null Publishers before returning
- User Need to convert Class to Json String
  - User wants a Json string of the class
    - Override ToString()

 ```` C#
 public override string ToString()
        {
            return JsonSerializer.Serialize<PublisherModel>(this);
        }
````

## Phase 3. Data Context

 1. Add the following NuGet packages to  the **GameService**

- Microsoft.EntityFrameworkCore
- Microsoft.EntityFrameworkCore.SqlServer
- Microsoft.EntityFrameworkCore.Tools

2. in the **GameService** create a folder **Context**
3. Create a class **GameDataContext** and inherit **DbContext**
4. Inject **IConfiguration** in the Constructor

5. The GameDataContext should have a DbSet property for the PublisherModel
6. The GameDataContext should have a DbSet property for the BoradGameModel
7. You will need to override the OnConfiguring method

```` C#
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
````

8. override the OnModelCreating
  - Add your seed Data for the Publisher
  - Add Your seed Data for the Board Games
**Example**

````C#
 //ToDo Must seed the publisher first
    modelBuilder.Entity<PublisherModel>().HasData(
    new PublisherModel()
    {
        Id = 1,
        Name = "Days of Wonder"
        },
````
9. add Default Connection string to the appSettings.json
```` json
{
  "Logging": {
    "LogLevel": {
      "Default": "Information",
      "Microsoft.AspNetCore": "Warning"
    }
  },
  "ConnectionStrings": {
    "DefaultConnection": "Server=(localdb)\\mssqllocaldb;Database=GameSolution;Trusted_Connection=True;"
  },
  "AllowedHosts": "*"
}

````
10. add Default Connection string to the appSettings.Development.json
```` json
{
  "ConnectionStrings": {
    "DefaultConnection": "Server=(localdb)\\mssqllocaldb;Database=GameSolution_dev;Trusted_Connection=True;"
  },
  "Logging": {
    "LogLevel": {
      "Default": "Information",
      "Microsoft.AspNetCore": "Warning"
    }
  }
}

````
## Phase 4. Data Services
> the GameService will take care of the Database and the access services.

1. Create a folder for Services in the GameService project
2. Create an interface **IService** add CRUD methods
- ValueTask<TEntity> **AddAsync**(TEntity entity, CancellationToken cancellationToken = default(CancellationToken));
- ValueTask<bool> **DeleteAsync**(TEntity entity, CancellationToken cancellationToken = default(CancellationToken));
- ValueTask<List<TEntity>> **GetAllAsync**(CancellationToken cancellationToken = default(CancellationToken));
- ValueTask<TEntity?> **GetAsync**(int id, CancellationToken cancellationToken = default(CancellationToken));
- ValueTask<TEntity> **UpdateAsync**(TEntity entity, CancellationToken cancellationToken = default(CancellationToken));

1. Create an **IPublisherService**, it should inherit the **IService** interface
2. Create an **IBoardGameService** that inherits the **IService** interface
3. Create **Service** class that implaments the **IService** interface
4. Create a **PublisherService** that inherits the **Service** class and implaments the **IPublisherService** interface
5. Create a **BoardGameService** that inherits the **Service** class and implaments the **IBoardGameService** interface

## Phase 5. Dependance Injection

>We will create a Startup Extension Class to handle the injection of service. 

1. Create a static **Startup** Class in the root folder of the GameService

````C#
  public static IServiceCollection AddDbService(this IServiceCollection service)
  {
      service.AddDbContext<GameDataContext>();
      service.AddScoped<IPublisherService, PublisherService>();
      service.AddScoped<IBoardGameService, BoardGameService>();
      return service;
  }
  ````
2. Add the following to the **Program** class
`````C#
// Add services to the container.

builder.Services.AddDbService();

// Added this to prevent Reference Cycles
builder.Services.AddControllers().AddJsonOptions(x =>
                x.JsonSerializerOptions.ReferenceHandler = ReferenceHandler.IgnoreCycles);
`````
## Phase 6. Data Migration

 in the "Package Manager Console"

1. Type "Add-Migration initial"
2. Type "update-database"
3. Correct any bugs then repeat Phase 5..

## Phase 7. Data Repositories

> The Data Repositories will connect the MVC UI to the Data Service API.

1. In the **GameLibrary** create a folder **Repositories**

2. Create Interface "IRepository"
     This interface will be the primary interface for all the CRUD operations

``` c#
    public interface IRepository<TEntity> where TEntity : class
    {
        ValueTask<TEntity> AddAsync(TEntity entity, CancellationToken cancellationToken = default(CancellationToken));
        ValueTask<bool> DeleteAsync(TEntity entity, CancellationToken cancellationToken = default(CancellationToken));
        ValueTask<List<TEntity>> GetAllAsync(CancellationToken cancellationToken = default(CancellationToken));
        ValueTask<TEntity?> GetAsync(int id, CancellationToken cancellationToken = default(CancellationToken));
        ValueTask<TEntity> UpdateAsync(TEntity entity, CancellationToken cancellationToken = default(CancellationToken));
    }
```

3. Create Interface "IPublisherRepository"
   - inherit IRepository
   This interface will serve for the CURD operations for PublisherModel

``` c#
  public interface IPublisherRepository : IRepository<PublisherModel>
    {
     
    }
````

4. Create Interface "IBoardGameRepository"
    - inherit IRepository
     This interface will serve for the CURD operations for BoardGameModel
     In additon we will return a list of publishers for the create and update dropdown box

```` c#
 public interface IBoardGameRepository : IRepository<BoardGameModel>
    {
        ValueTask<IEnumerable<PublisherModel>> GetPublishers(CancellationToken cancellationToken = default(CancellationToken));
    }
````

5. Add extension class ApiHelper

```` C#
   public static async Task<TData> ReadContentAsync<TData>(this HttpResponseMessage response,CancellationToken cancellationToken = default(CancellationToken))
  {
      if (response.IsSuccessStatusCode == false)
          throw new ApplicationException($"Something went wrong calling the API: " + response.ReasonPhrase);

      String? dataAsString = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

      TData? result = JsonSerializer.Deserialize<TData>(
          dataAsString, new JsonSerializerOptions
          {
              PropertyNameCaseInsensitive = true
          });
      if(result == null )
      {
          throw new ApplicationException($"Something went wrong calling the API: results was null");
      }
      return result;
  }
 ````

6. Add BoardGameRepository class and PublisherRepository class (They should match their respective interface)


### Phase 8. MVC Controllers in GameLibrary

> The default behavior of the MVC with Entity framework will help, but the underline code will need to be replaced with the reference to the repositories

- PublisherController
    1. User can display all Publishers
    2. User can add new Publishers
    3. User Can edit exisiting publishers
    4. User can display details of a single publisher
    5. User can delete publisher

- BoardGameController
  1. User can display all Boardgames
  2. User can add new Boardgames
  3. User Can edit exisiting Boardgames
  4. User can display details of a single Boardgame
  5. User can delete Boardgames

1. add a using statement to Program.cs in GameLibrary

````c#
using GameDataLibrary;
````

2. add builder.Services.AddDbService() after builder.Services.AddControllersWithViews()

```` c#
// Add services to the container.
builder.Services.AddControllersWithViews();

// Add Database services
builder.Services.AddDbService();
````

3. Add PublisherController to GameLibrary

- Right click on the Controllers Folder in the GameLibrary Project
- Select Add
- Select Controllers
- Select MVC Controller with Views, using Entity Framework
- Click Add
- Data Model should be "PublisherModel"
- Data Context should be "GameDataContext"
- Change Controller name to "PublisherController"

4. Add BoardGameController to GameLibrary

- Right click on the Controllers Folder in the GameLibrary Project
- Select Add
- Select Controllers
- Select MVC Controller with Views, using Entity Framework
- Click Add
- Data Model should be "BoardGameModel"
- Data Context should be "GameDataContext"
- Change Controller name to "BoardGameController"

> Run the applicaion check to make sure things are working

5. Modifiy PublisherController
6. Modifiy Private fields

````c#
 private readonly ILogger<PublisherController> logger;
 private readonly IPublisherRepository repository;
````

7. Modifiy Constructor

```` c#
public PublisherController(ILogger<PublisherController> logger, IPublisherRepository repository)
 {
    this.logger = logger ?? throw new System.ArgumentNullException(nameof(logger));
    this.repository = repository ?? throw new System.ArgumentNullException(nameof(repository));
    logger.LogInformation($"PublisherController started");
 }
````

8. Modifiy Index

````c#
public async Task<IActionResult> Index()
{
    logger.LogInformation($"{nameof(Index)}");
    return View(await repository.GetAllAsync());
}
````

9. Modifiy Details

```` c#
public async Task<IActionResult> Details(int id)
{
    logger.LogInformation($"{nameof(Details)}");
    var publisherModel = await repository.GetAsync(id);
    if (publisherModel == null)
    {
        logger.LogInformation($"{nameof(Details)} of {id} not found");
        return NotFound();
    }

    return View(publisherModel);
}
````

10. Modifiy Create

```` c#
public IActionResult Create()
{
    logger.LogInformation($"{nameof(Create)}");
    return View();
}

````

11. Modifiy Create save

```` c#
public async Task<IActionResult> Create([Bind("Id,Name")] PublisherModel publisherModel)
{
    logger.LogInformation($"{nameof(Create)} Save model");
    if (ModelState.IsValid)
    {
        await repository.AddAsync(publisherModel);
        return RedirectToAction(nameof(Index));
    }
    else
    {
       logger.LogInformation($"{nameof(Create)} invalid model");
    }
    return View(publisherModel);
}
````

12. Modifiy Edit

```` C#
public async Task<IActionResult> Edit(int id)
{
    logger.LogInformation($"{nameof(Edit)}");
    var publisherModel = await repository.GetAsync(id);
    if (publisherModel == null)
    {
        logger.LogInformation($"{nameof(Edit)} of {id} not found");
        return NotFound();
    }
    return View(publisherModel);
}
````

13. Modifiy Edit Save

```` c#
public async Task<IActionResult> Edit(int id, [Bind("Id,Name")] PublisherModel publisherModel)
{
    logger.LogInformation($"{nameof(Edit)} Save");
    if (ModelState.IsValid)
    {
        await repository.UpdateAsync(publisherModel);
        return RedirectToAction(nameof(Index));
    }
    else
    {
        logger.LogInformation($"{nameof(Edit)} invalid model.");
    }
    return View(publisherModel);
}
````

14. Modifiy Delete

```` c#
public async Task<IActionResult> Delete(int id)
{
    logger.LogInformation($"{nameof(Delete)} {id}");
    var publisherModel = await repository.GetAsync(id);

    if (publisherModel == null)
    {
        logger.LogInformation($"{nameof(Delete)} {id} not found");
        return NotFound();
    }

    return View(publisherModel);
}
````

15. Modifiy Delete Confirmed

```` c#
public async Task<IActionResult> DeleteConfirmed(int id)
{
    logger.LogInformation($"{nameof(DeleteConfirmed)} {id}");
    var publisherModel = await repository.GetAsync(id);
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
````

> Run the applicaion check to make sure things are working

16. Modifiy BoardGameController
17. Modifiy Private fields

````c#
 private readonly ILogger<BoardGameController> logger;
 private readonly IBoardGameRepository repository;
````

18. Modifiy Constructor

```` c#
public BoardGameController(ILogger<BoardGameController> logger, IBoardGameRepository repository)
{
    this.logger = logger ?? throw new System.ArgumentNullException(nameof(logger));
    this.repository = repository ?? throw new System.ArgumentNullException(nameof(repository));
    logger.LogInformation($"BoardGameController started");
}
````

19. Modifiy Index

````c#
public async Task<IActionResult> Index()
{
    logger.LogInformation($"{nameof(Index)}");
    return View(await repository.GetAllAsync());
}
````

20. Modifiy Details

```` c#
public async Task<IActionResult> Details(int id)
{

    logger.LogInformation($"{nameof(Details)} of {id}");
    var boardGameModel = await repository.GetAsync(id);

    if (boardGameModel == null)
    {
        logger.LogInformation($"{nameof(Details)} of {id} not found");
        return NotFound();
    }

    return View(boardGameModel);
}
````

21. Modifiy Create

```` c#
public async Task<IActionResult> Create()
{
    logger.LogInformation($"{nameof(Create)}");
    ViewData["PublishersId"] = new SelectList(await repository.GetPublishers(), "Id", "Name");
    return View();
}
````

22. Modifiy Create save

```` c#
public async Task<IActionResult> Create([Bind("Id,Name,Description,PublishersId,ImageURL")] BoardGameModel boardGameModel)
{
        logger.LogInformation($"{nameof(Create)} Save model");
        if (ModelState.IsValid)
    {
        await repository.AddAsync(boardGameModel);
        logger.LogInformation($"{nameof(Create)} new BoardGame {boardGameModel}");
        return RedirectToAction(nameof(Index));
    }
    else
    {
        logger.LogInformation($"{nameof(Create)} invalid model");
    }
    ViewData["PublishersId"] = new SelectList(await repository.GetPublishers(), "Id", "Name");
    return View(boardGameModel);
}
````

23. Modifiy Edit

```` C#
public async Task<IActionResult> Edit(int id)
{
    logger.LogInformation($"{nameof(Edit)}");
    var boardGameModel = await repository.GetAsync(id);
    if (boardGameModel == null)
    {
        logger.LogInformation($"{nameof(Edit)} BoardGameModel not found");
        return NotFound();
    }
    ViewData["PublishersId"] = new SelectList(await repository.GetPublishers(), "Id", "Name");
    return View(boardGameModel);
}
````

24. Modifiy Edit Save

```` c#
public async Task<IActionResult> Edit(int id, [Bind("Id,Name,Description,PublishersId,ImageURL")] BoardGameModel boardGameModel)
{
    logger.LogInformation($"{nameof(Edit)} Save");
   if (ModelState.IsValid)
    {
        await repository.UpdateAsync(boardGameModel);
        return RedirectToAction(nameof(Index));
    }
    else
    {
        logger.LogInformation($"{nameof(Edit)} invalid model.");
    }
    ViewData["PublishersId"] = new SelectList(await repository.GetPublishers(), "Id", "Name");
    return View(boardGameModel);
}
````

25. Modifiy Delete

```` c#
public async Task<IActionResult> Delete(int id)
{
    logger.LogInformation($"{nameof(Delete)} {id}");
    var boardGameModel = await repository.GetAsync(id);
    if (boardGameModel == null)
    {
        logger.LogInformation($"{nameof(Delete)} BoardGameModel not found");
        return NotFound();
    }
    return View(boardGameModel);
}
````

26. Modifiy Delete Confirmed

```` c#
public async Task<IActionResult> DeleteConfirmed(int id)
{
    logger.LogInformation($"{nameof(DeleteConfirmed)} {id}");
    var boardGameModel = await repository.GetAsync(id);
    if (boardGameModel != null)
    {
        await repository.DeleteAsync(boardGameModel);
    }
    else
    {
        logger.LogInformation($"{nameof(Delete)} BoardGameModel not found, can not delete");
    }
    return RedirectToAction(nameof(Index));
}
````

> Run the applicaion check to make sure things are working

## Step 8. Modifity Views

1. BoardGame Delete View

- Add link to Publisher Details to replace publisher Name

````HTML
  <dd class="col-sm-10">
        <a asp-route-id="@Model.PublishersId" asp-controller="Publisher" asp-action="Details">@Model.Publisher</a>
  </dd>
````

- Add img display to replace ImageURL display

````HTML
<dd class="col-sm-10">
   <img class="rounded-circle" src="@Model.ImageURL" alt="@Model.Name"
       style="max-width: 150px">
</dd>
````

2. BoardGame Details View

- Add link to Publisher Details to replace publisher Name

````HTML
  <dd class="col-sm-10">
        <a asp-route-id="@Model.PublishersId" asp-controller="Publisher" asp-action="Details">@Model.Publisher</a>
  </dd>
````

- Add img display to replace ImageURL display

````HTML
<dd class="col-sm-10">
   <img class="rounded-circle" src="@Model.ImageURL" alt="@Model.Name"
       style="max-width: 150px">
</dd>
````

3. BoardGame Index View

- Move the image column to column 0 (the 1st)

````HTML
 <tr>
    <th>
      @Html.DisplayNameFor(model => model.ImageURL)
    </th>
    <th>
      @Html.DisplayNameFor(model => model.Name)
    </th>
    <th>
      @Html.DisplayNameFor(model => model.Description)
    </th>
     <th>
        @Html.DisplayNameFor(model => model.Publishers)
     </th>
         
    <th></th>
</tr>
````

- Add img display to replace ImageURL display

````HTML
<td>
   <img class="rounded-circle" src="@item.ImageURL" alt="@item.Name"
           style="max-width: 150px">
</td>
````

- Add Actionlink to Publisher Details to replace publisher Name

````HTML
   <td>
       @Html.ActionLink(item.Publisher,
            "Details",
            "Publisher",
              new { id = item.PublishersId })
  </td>
````

4. Publisher Delete View

- Add Warning Message at the top

````HTML
<h2 class="text-danger">Deleting this Publisher will delete all Board Games</h2>
````

-Add display for the Board Games Associated with publisher at the bottom of the screen

````HTML
  @if (Model.BoardGames != null)
    {
           <dl class="row">
            @foreach (var b in Model.BoardGames)
            {
                    <dt class = "col-sm-2">
                        <a asp-route-id="@b.Id" asp-controller="BoardGame" asp-action="Details">@b.Name</a>
                    </dt>
                     <dd class = "col-sm-10">
                            <img class="rounded-circle" src="@b.ImageURL" alt="@b.Name"
                 style="max-width: 150px">     
                    </dd>    

            }
            </dl>
    }
````

5. Publisher Details View

- -Add display for the Board Games Associated with publisher at the bottom of the screen

````HTML
     @if (Model.BoardGames != null)
    {
        <dl class="row">
            @foreach (var b in Model.BoardGames)
            {
                <dt class="col-sm-2">
                    <a asp-route-id="@b.Id" asp-controller="BoardGame" asp-action="Details">@b.Name</a>
                </dt>
                <dd class="col-sm-10">
                    <img class="rounded-circle" src="@b.ImageURL" alt="@b.Name"
                 style="max-width: 150px">
                </dd>

            }
        </dl>
    }
````

6. Shared _Layout view

- Add menu links for Board Games and Publisher

````HTML
 <li class="nav-item">
    <a class="nav-link text-dark" asp-area="" asp-controller="BoardGame" asp-action="Index">Board Games</a>
 </li>
 <li class="nav-item">
    <a class="nav-link text-dark" asp-area="" asp-controller="Publisher" asp-action="Index">Publisher</a>
 </li>
````

## To set multiple startup projects

1. In Solution Explorer, select the solution (the top node).
2. Choose the solution node's context (right-click) menu and then choose Properties. ...
3. Expand the Common Properties node, and choose Startup Project.
4. Choose the Multiple Startup Projects option and set the appropriate actions.

> You must start the GameService before starting the GameLibrary
