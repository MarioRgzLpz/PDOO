require_relative 'persona'

class Cosa
    @@Maximo = 3

    def initialize(nombre)
        @nombre = nombre
    end

    def self.Maximo
        @@Maximo
    end

    def copia(otro)

        @nombre=nombre
    end

    private
    def nombre
        @nombre
    end

    public
    def self.nombre
        
    end
end

t1 = Cosa.new("Mochila")
t2 = Cosa.new("Libro")
t1.copia(t2)
puts t1.nombre
