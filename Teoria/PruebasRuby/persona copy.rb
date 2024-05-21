require_relative 'cosa'


class Persona
    @@num_personas = 0
    @@MaximoPermitido = Cosa.Maximo
    def initialize(nombre, edad, color_pelo)
        @nombre = nombre
        @edad = edad
        @color_pelo = color_pelo
        @@num_personas += 1
    end

    def saluda
        puts "Hola, soy #{@nombre}"
    end

    def cambia_nombre (otronombre)
        @nombre = otronombre
    end

    def self.num_personas
        puts "#{@@num_personas}"
    end
end