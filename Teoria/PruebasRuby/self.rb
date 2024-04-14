#encoding: UTF-8
module Teoria
    class Cosa
        @@MAYOR = 18
        @nombre = "Atributo de instancia de clase "
        def initialize(nombre)
            @nombre = nombre
        end

        def nombre
            return @nombre
        end

        def self.nombre
            return @nombre
        end

        def to_s
            return "Me llamo " + self.nombre # Esta haciendo una llamada al metodo de instancia nombre que devuelve el atributo @nombre 
        end

        def self.mayor # Metodo de clase que devuelve la constante de clase MAYOR
            #puts self.nombre # Error: undefined method `nombre' for Teoria::Cosa:Class Estamos haciendo una llamada a un metodo de instancia desde ambito de clase
            puts @@MAYOR
            puts nombre #Como estoy en ambito de clase, se esta llamando al metodo de clase self.nombre que devuelve el atributo de instancia de clase @nombre
        end
    end
end

cosa = Teoria::Cosa.new("Cosa1")
puts cosa.to_s
Teoria::Cosa.mayor