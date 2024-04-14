class Clase
    @@variable = "De clase"
    @variable = "De instancia de clase"

    def initialize
        @variable = "De instancia"
    end

    def muestra_valores
        puts @@variable #Este es de clase siempre
        puts @variable  #Este es de instancia ya que estamos accediendo en un metodo de instancia y por tanto ambito de instancia
    end

    def self.muestra_valores
        puts @@variable #Este es de clase siempre
        puts @variable  #Este es de instancia de clase ya que estamos en un metodo de clase y por tanto en ambito de clase
    end
end

objeto = Clase.new
objeto.muestra_valores
Clase.muestra_valores