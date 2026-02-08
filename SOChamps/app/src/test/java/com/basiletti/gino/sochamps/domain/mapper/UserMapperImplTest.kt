package com.basiletti.gino.sochamps.domain.mapper

import com.basiletti.gino.sochamps.util.generateUserDtoModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class UserMapperImplTest {
    private lateinit var sut: UserMapperImpl

    @Before
    fun setUp() {
        sut = UserMapperImpl()
    }

    @Test
    fun userMapper_convertDtoToPresentation_returnsCorrectUser() {
        val userDtoList = mutableListOf(
            generateUserDtoModel(),
            generateUserDtoModel(),
            generateUserDtoModel()
        )

        val result = sut.convertDtoToPresentation(userDtoList)
        assertEquals(result.size, 3)

        val dtoUser = userDtoList[0]
        val presentationUser = result[0]

        assertEquals(presentationUser.id, dtoUser.user_id)
        assertEquals(presentationUser.name, dtoUser.display_name)
        assertEquals(presentationUser.profileImageURL, dtoUser.profile_image)
        assertEquals(presentationUser.reputation, dtoUser.reputation)
        assertEquals(presentationUser.bronzeBadges, dtoUser.badge_counts.bronze)
        assertEquals(presentationUser.silverBadges, dtoUser.badge_counts.silver)
        assertEquals(presentationUser.goldBadges, dtoUser.badge_counts.gold)
    }

    @Test
    fun userMapper_convertDtoToPresentation_orderingIsPreserved() {
        val userDtoList = mutableListOf(
            generateUserDtoModel(displayName = "Adam"),
            generateUserDtoModel(displayName = "Ben"),
            generateUserDtoModel(displayName = "Charlie")
        )

        val result = sut.convertDtoToPresentation(userDtoList)
        assertEquals(result.size, 3)

        assertEquals("Adam", userDtoList[0].display_name)
        assertEquals("Adam", result[0].name)

        assertEquals("Ben", userDtoList[1].display_name)
        assertEquals("Ben", result[1].name)

        assertEquals("Charlie", userDtoList[2].display_name)
        assertEquals("Charlie", result[2].name)
    }


}