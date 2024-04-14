#encoding: UTF-8
module Teoria
    class Prueba
        @@variable = "De clase"
        @variable = "De instancia de clase"
    
        def initialize
            @variable = "De instancia"
            metodo_instancia_privado
            metodo_instancia_publico
            self.metodo_instancia_privado
            self.metodo_instancia_publico
        end
    
        def metodo_instancia_publico
            puts "Instancia Publico"
        end
    
        def self.metodo_clase_publico
            puts "Clase Publico"
        end

        def metodo_instancia_privado
            puts "Instancia Privado"
        end
    
        def self.metodo_clase_privado
            puts "Clase Privado"
        end

        def uso_dentro_clase
            metodo_instancia_privado
            self.metodo_instancia_privado
            metodo_instancia_publico
            self.metodo_instancia_publico
            # metodo_clase_publico #Error: Estamos llamando a metodos de clase desde ambito de instancia da igual la visibilidad que tengamos
            # self.metodo_clase_publico
            # metodo_clase_privado 
            # self.metodo_clase_privado
        end

        def self.uso_dentro_clase
            # metodo_instancia_privado
            # self.metodo_instancia_privado
            #metodo_instancia_publico
            # self.metodo_instancia_publico
            metodo_clase_publico #Error: Estamos llamando a metodos de clase desde ambito de instancia da igual la visibilidad que tengamos
            self.metodo_clase_publico
            metodo_clase_privado 
            self.metodo_clase_privado
        end       

        def uso_otro_objeto
            otro = Prueba.new
            # otro.metodo_instancia_privado Estamos llamando a un metodo privado de instancia de otro objeto en este caso prueba
            otro.metodo_instancia_publico # como es publico si se puede
            otro.uso_dentro_clase #Esta llamando a los metedos de instancia de el mismo no de la instancia prueba
        end
        private :metodo_instancia_privado

        private_class_method :metodo_clase_privado
    end
end

prueba = Teoria::Prueba.new
prueba.metodo_instancia_publico
Teoria::Prueba.metodo_clase_publico
prueba.uso_dentro_clase
Teoria::Prueba.uso_dentro_clase
prueba.uso_otro_objeto
#prueba.metodo_instancia_privado #Error: private method `metodo_instancia_privado' called for #<Teoria::Prueba:0x0000000004b3b8>
#Teoria::Prueba.metodo_clase_privado #Error: private method `metodo_clase_privado' called for Teoria::Prueba:Class