## Description

An app created for a technical test which displays the top 20 Stack Overflow users

## Functionality

This app enables a user to see the top 20 contributors of StackOverflow. It loads them in upon opening the app, and displays their profile image, name, reputation, bronze/silver/gold badge counts. It handles a few different states also; such as displaying a loading state and also an empty state (likely encountered if offline). 

There is also a simulation of being able to follow/unfollow a user via the follow button beneath their profile image. The follow button reflects their followed state by text and colour; and clicking it to unfollow a user will display a warning dialog - much like we may see on social media apps like Twitter for example.

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

