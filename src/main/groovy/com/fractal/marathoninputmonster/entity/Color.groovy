package com.fractal.marathoninputmonster.entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Entity



/**
 *
 *
 * Created by mikeyb on 2/14/19.
 */


@Entity
class Color{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    String name
}