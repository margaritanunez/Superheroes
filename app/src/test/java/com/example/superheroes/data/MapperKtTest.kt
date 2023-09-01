package com.example.superheroes.data

import com.example.superheroes.data.remote.DetailSuperheroe
import com.example.superheroes.data.remote.Superheroe
import org.junit.Assert.*

import org.junit.Test

class MapperKtTest {

    @Test
    fun transformToEntity() {
        //Given
        val superheroeTestUnitario = Superheroe(2,"batman", "kansas city","example.com","ser millonario", 1923)

        //When
        val result = superheroeTestUnitario.transformToEntity()

        //Then
        assertEquals(superheroeTestUnitario.id, result.id)
        assertEquals(superheroeTestUnitario.name, result.name)
        assertEquals(superheroeTestUnitario.origin, result.origin)
        assertEquals(superheroeTestUnitario.imageLink,result.imageLink)
        assertEquals(superheroeTestUnitario.power,result.power)
        assertEquals(superheroeTestUnitario.yearCreation,result.yearCreation)

    }

    @Test
    fun transformToDetailEntity() {
        //Given
        val superheroeTestUnitario = DetailSuperheroe(2,"batman", "kansas city","example.com","ser millonario", 1923, "black", false)

        //When
        val result = superheroeTestUnitario.transformToDetailEntity()

        //Then
        assertEquals(superheroeTestUnitario.id, result.id)
        assertEquals(superheroeTestUnitario.name, result.name)
        assertEquals(superheroeTestUnitario.origin, result.origin)
        assertEquals(superheroeTestUnitario.imageLink,result.imageLink)
        assertEquals(superheroeTestUnitario.power,result.power)
        assertEquals(superheroeTestUnitario.yearCreation,result.yearCreation)
        assertEquals(superheroeTestUnitario.colour, result.colour)
        assertEquals(superheroeTestUnitario.translation, result.translation)

    }
}