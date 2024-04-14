#encoding: UTf-8

require_relative 'persona'
require_relative 'color_pelo'
require_relative 'producto'

class Test1
    def initialize
        @persona = Teoria::Persona.new("Mario", 22, Teoria::ColorPelo::MORENO)
    end

    def main
        @persona.saluda
        @persona.cambia_nombre ("Pepe")
        @persona.saluda
        Teoria::Persona.num_personas
        puts "Creo otra persona"
        @persona2 = Teoria::Persona.new("Juan", 32, Teoria::ColorPelo::RUBIO)
        @persona2.saluda
        Teoria::Persona.num_personas

        puts "Clase Producto: Constantes/Atributos/Variables locales"
        producto = Teoria::Producto.new("Producto1", 10)
        producto.to_s # otro_iva no se muestra porque es de instancia de clase y lo estamos llamando desde instancia
        Teoria::Producto.to_s #No se muestran las variables de instancia desde clase porque no estamos en ambito de instancia
        puts "Cambio desde instancia"
        producto.instancia_set_IVA(15, 5, 11)
        Teoria::Producto.to_s #No se ha cambiado el valor de otro_iva porque desde instancia no se puede
        producto.to_s # No se esta mostrando otro iva si no que al llamar a set_IVA desde la instancia se ha creado una variable de instancia nueva llamada otro_iva con el valor pasado
        puts producto.inspect #Aqui vemos como ahora tiene tres variables de instancia
        puts "Cambio desde clase"
        Teoria::Producto.clase_set_IVA(18, 7, 8)
        Teoria::Producto.to_s #Se ha cambiado el valor de otro_iva porque desde clase si se puede
        producto.to_s 

        puts "Impuesto minimo #{Teoria::Producto::IMPUESTOMINIMO}" #Acceso a una constante ya que son publicas

        # producto.clase_set_IVA(20) #No se puede acceder a un metodo de clase desde una instancia
    end
end

test1 = Test1.new
test1.main