require_relative 'persona'

class Cosa
    @@Maximo = 3

    attr_reader :nombre

    def initialize(nombre)
        @nombre = nombre
    end

    def self.Maximo
        @@Maximo
    end
end
