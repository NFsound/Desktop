package models.wrappers.music

import models.core.networks.GenerationParams

class GenerationBody(val byteArray: ByteArray,
                     val generationParams: GenerationParams) {
}