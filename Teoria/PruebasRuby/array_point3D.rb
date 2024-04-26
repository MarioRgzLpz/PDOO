#encoding: UTF-8
module Teoria
    class ArrayPoint3D
        @@MAX = 100
        @@MIN = 0
        def initialize(*args)
            @x, @y, @z = args.map { |arg| restrict(arg) }
            @x ||= 0 #Si no se ha pasado x, y o z se inicializa a 0
            @y ||= 0
            @z ||= 0
        end

        def restrict (a)
            result = [@@MIN , a].max
            result = [@@MAX, result].min
            return result
        end

        def imprimir
            puts "x: #{@x}, y: #{@y}, z: #{@z}"
        end
    end
end

pointX = Teoria::ArrayPoint3D.new(10)
pointXY = Teoria::ArrayPoint3D.new(-7, 290)
pointXYZ = Teoria::ArrayPoint3D.new(10, 20, 35)
point = Teoria::ArrayPoint3D.new()
pointX.imprimir
pointXY.imprimir
pointXYZ.imprimir
point.imprimir