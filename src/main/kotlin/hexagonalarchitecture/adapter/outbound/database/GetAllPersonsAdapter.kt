package hexagonalarchitecture.adapter.outbound.database

import hexagonalarchitecture.application.domain.model.Maturity
import hexagonalarchitecture.application.domain.model.Person
import hexagonalarchitecture.application.domain.model.Sex
import hexagonalarchitecture.application.port.outbound.GetAllPersonsPort
import org.springframework.stereotype.Component

@Component
class GetAllPersonsAdapter(): GetAllPersonsPort {
    override fun get(): List<Person> {
        val person1 = Person("Vasco Lusitano", Sex.MASCULINE, 27, Maturity.ADULT)
        val person2 = Person("Rita Lima", Sex.FEMININE, 26, Maturity.ADULT)
        return listOf(person1, person2)
    }
}