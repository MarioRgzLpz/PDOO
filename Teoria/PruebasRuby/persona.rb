#encoding: UTF-8

require_relative 'cosa'


class Persona
    @@MaximoPermitido = Cosa.Maximo
    def initialize(nombre)
        @nombre = nombre
        @cosas = []
    end

    def saluda
        puts "Hola, soy #{@nombre}"
    end

    def cambia_nombre (otronombre)
        @nombre = otronombre
    end

    def otra_cosa(cosa)
        if(@cosas.size < @@MaximoPermitido)
            @cosas.push(cosa)
        end
    end

    def to_s
        salida = "Soy #{@nombre} y tengo"
        @cosas.each do |cosa|
            salida += " #{cosa.nombre}"
        end
        salida
    end
end
