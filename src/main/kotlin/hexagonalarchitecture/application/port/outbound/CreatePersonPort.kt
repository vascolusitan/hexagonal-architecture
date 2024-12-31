package hexagonalarchitecture.application.port.outbound

import hexagonalarchitecture.application.dto.CreatePersonDto

interface CreatePersonPort {

    fun save(createPersonDto: CreatePersonDto)

}