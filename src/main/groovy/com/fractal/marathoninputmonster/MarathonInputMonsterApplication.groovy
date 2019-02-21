package com.fractal.marathoninputmonster

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MarathonInputMonsterApplication {
	private static final Logger log = LoggerFactory.getLogger(MarathonInputMonsterApplication)
	static void main(String[] args) {
		SpringApplication.run(MarathonInputMonsterApplication, args)
	}

}

