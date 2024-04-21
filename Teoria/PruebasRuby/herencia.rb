#enconding: utf-8

class Padre
    @altos_instancia_clase = 0
    @@altos = 0
    def initialize(edad,altura)
        @edad = edad
        @altura = altura
        if(altura > 1)
            @@altos += 1
        end
    end
    
    def salida
        puts @edad
        puts @altura
    end

    def self.contador_altos(altura)
        @altos_instancia_clase += 1
    end

    def self.altos
        puts @altos_instancia_clase
    end

    def altos
        puts @@altos
    end

    def privado
        puts "Soy privado"
    end

    private :privado
end

class Hijo < Padre
    def initialize(peso)
        super(10,1.5)
        @peso = peso
    end

    def salida
        super
        puts @peso
    end

    def prueba
        privado
    end
end


Padre.new(45,1.8).altos
Padre.contador_altos(1.8) 
Padre.altos
Padre.new(30,1.8).altos
Padre.altos
Padre.new(22,1.8).altos
Padre.altos
Padre.new(41,1.8).altos
Padre.altos




#hijo = Hijo.new


hijo2 = Hijo.new(15).prueba