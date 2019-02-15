package com.fractal.marathoninputmonster.repo
import com.fractal.marathoninputmonster.entity.Input
import org.springframework.data.repository.CrudRepository
/**
 * Created by mikeyb on 2/14/19.
 */


interface InputRepository extends CrudRepository<Input,Long>{}
