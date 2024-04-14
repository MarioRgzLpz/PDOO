#encoding: UTF-8
module Teoria
    class Producto
        @@IVA = 21
        @otro_iva = 19
        IMPUESTOMINIMO = 10
        def initialize(nombre, precio)
            @nombre = nombre
            @precio = precio
        end

        def to_s
            puts "Desde instancia-----Nombre: #{@nombre}, Precio: #{@precio}, IVA: #{@@IVA}, Otro IVA: #{@otro_iva}, Impuesto minimo: #{IMPUESTOMINIMO}"
        end

        def self.to_s
            puts "Desde clase-----Nombre: #{@nombre}, Precio: #{@precio}, IVA: #{@@IVA}, Otro IVA: #{@otro_iva}, Impuesto minimo: #{IMPUESTOMINIMO}"
        end

        def instancia_set_IVA (iva, otro, impuesto)
            @@IVA = iva
            @otro_iva = otro
            # IMPUESTOMINIMO = impuesto  #Error: dynamic constant assignment
        end


        def self.clase_set_IVA (iva, otro, impuesto)
            @@IVA = iva
            @otro_iva = otro 
            # IMPUESTOMINIMO = impuesto 
        end
    end
end