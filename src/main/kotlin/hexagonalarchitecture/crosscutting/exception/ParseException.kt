package hexagonalarchitecture.crosscutting.exception

class ParseException(objectType: String, value: String) :
    RuntimeException("Could not parse to $objectType from $value")