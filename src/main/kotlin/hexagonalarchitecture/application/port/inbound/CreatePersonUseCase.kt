package hexagonalarchitecture.application.port.inbound

import hexagonalarchitecture.application.dto.CreatePersonDto

interface CreatePersonUseCase {

    fun process(createPersonDto: CreatePersonDto)

}
