## Description

An app created for a technical test which displays the top 20 Stack Overflow users

## Functionality

This app enables a user to see the top 20 contributors of StackOverflow. It loads them in upon opening the app, and displays their profile image, name, reputation, bronze/silver/gold badge counts. It handles a few different states also; such as displaying a loading state and also an empty state (likely encountered if offline). 

There is also a simulation of being able to follow/unfollow a user via the follow button beneath their profile image. The follow button reflects their followed state by text and colour; and clicking it to unfollow a user will display a warning dialog - much like we may see on social media apps like Twitter for example.

<img width="424" height="945" alt="image" src="https://github.com/user-attachments/assets/4c1802f3-ee96-450d-88f0-96e15dbe217f" />

## Implementation

-This application follows a MVVM architecture pattern

-Hilt has been used for dependency injection

-Retrofit is used for making calls to the SO API

-RoomDB is used for the persistence of follow states. For such a simple implementation requirement I did just consider storing a set in SharedPreferences, but it felt a bit outside of the norm to do that and isn't what I would ever really do in a real application anyway.

A clean architecture approach has been taken for this project. This is because I wanted to emphasize on a separation of concerns for the layers found within the app. This has involved splitting the main/only screen in the app into the layers required to build it: Data, Domain & Presentation.

-The data layer concerns the fetching of the data from the SO API, where the response is modelled to a 'DTO' model.

-The presentation layer involves all things view related; including the composables and the viewModel used to display the data to the UI. This layer is only concerned with presenting the data to the user. 

-Finally, the domain layer is what translates the DTO models into a localised model appropriate for the presentation layer. This is done via the mapper class which maps the UserDTO model to the presentation User model. In essence, it strips out the additional unnecessary data from the DTO, and also adds a 'isFollowing' boolean - as this is something we later rely on from our presentation/UI layer, but is not given to us by the SO endpoint. 

This 'separation of concerns' among the 3 layers described above enables each layer to be more serviceable if changes are made concerning one of the layers. For example, if the API endpoint was to change the model in which it gives its data in the response, the presentation layer would remain totally unchanged. The work to implement such changes would be needed in the data layer (remodelling the DTO) and in the mapping functions, if required.

To further aid this approach, the package structure closely aligns to the 3 aforementioned layers, and are structured within the domain/presentation/data packages with each feature package.

Another thing to note with the implementation is that from the start, where composables were going to be reused (titles, icons, etc.), I broke these down into 'component' files; which are all kept under the components package. This just keeps things really tidy, improves the testability of the components seen on the screens themselves, and enables us to not repeat more code than necessary. In my other personal projects, these components are often moved to their own 'ui-widgets' module; which gives us the benefits of app modularisation too - I didn't feel like it was necessary to modularise them in this mini-project though.

## Testing

Test coverage has been provided for as much of the classes as possible within these layers - with the exception of the data layer.

-For the domain layer, the mapping function was tested to thoroughly ensure that the DTO classes were translated into our local model as we would expect them to be. This mapping would handle the very simple stripping down of the DTO model to just provide what we need on the presentation layer.

-The majority of the testing done was on the presentation layer. The viewModel was fully tested (arguably the most important area, where UI state should always reflect what we believe should be happening at various stages), and the composables were quite thoroughly tested using the composeRule. I tested the VM and the main screen, but also tested a few of the component composables. I didn't test every single component for the purposes of this technical test, but I covered a few just to show the general approach I would take with writing test coverage for small components. There ended up being 9 component files, but most of which were just really basic texts/buttons, so I left a few of those out for testing and focused on the slightly more complex ones.

## Future Improvements / Known Issues

-I could have added test coverage for the data layer of the app; namely around the repository / database parts, but decided against it to focus more on the other, more meaty classes and areas of the app. The DAO/database classes are extremely simple, and to write test coverage for them would have just mostly involved mocking what I would want them to return and verifying that they did that. There may have been more value in writing tests that verify that a row isn't inserted twice, a row is deleted when somebody is unfollowed etc. though; so I do acknowledge that this could have been more comprehensively tested.

-Furthermore, I could have written tests for the useCases. I feel like there would have been value in adding test coverage for the GetSOUsersUseCase in particular; as this attaches a Resource.Success/Error to the handled data, which would be very handy/meaningful to verify against when good or bad data is dealt with.

-Implementation wise, I am pleased with how the app is behaving. The only thing I am aware of that feels a bit off / improper, is the error banner is just fully displaying the error message returned from the API. This would obviously not mean an awful lot to an end-user (seeing something like a socket/timeout exception error message), and they would understand something more along the lines of 'Failed to reach server - you are offline'. A better/future implementation could have just been to have an errorCode: INT returned rather than an errorMessage, and then we could have the app translate that error code into some error message that we define ourselves - this would give us much better control over what error gets shown to the end user, rather than blurting out whatever the server sends us.

-I ran into some issues when making the DTO models. The end result is only a very small inconsistency, but I wanted to note it: the models are using snake_case in their variable names, rather than our usual camelCase approach. I had attempted to prepend every variable in the DTO model with @SerializedName("the_api_variable_name"), but for some reason was encountering some error where it was failing to map the response to the DTO model.

-As mentioned, I may have modularised certain parts of the app if I was spending longer on it. In my other personal project I have a :ui-widgets module and a :persistence module; where the composable components and roomDB/database implementation would both belong in their separate modules. Aside from feeling cleaner/more separated, this obviously helps with compilation / the separation of strings files etc. in larger scale apps. For an app with one screen and one small table in a database, this didn't feel necessary right now.
