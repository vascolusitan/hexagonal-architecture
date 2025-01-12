package hexagonalarchitecture.crosscutting.exception

class NotFoundException(source: String, value: String) :
    RuntimeException("No $source found for $value")