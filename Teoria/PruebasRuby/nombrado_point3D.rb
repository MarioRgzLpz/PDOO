#encoding: UTF-8
module Teoria
    class NombradoPoint3D
        @@MAX = 100
        @@MIN = 0
        def initialize(x:0, y:0, z:0) #Se inicializan a 0 si no se pasa nada
            @x = restrict(x)
            @y = restrict(y)
            @z = restrict(z)
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

pointX = Teoria::NombradoPoint3D.new(x:10)
pointY = Teoria::NombradoPoint3D.new(y:15)
pointZ = Teoria::NombradoPoint3D.new(z:110)
pointXY = Teoria::NombradoPoint3D.new(x:-7, y:290)
pointYZ = Teoria::NombradoPoint3D.new(y:-7, z:290)
pointXYZ = Teoria::NombradoPoint3D.new(y:10, z:20, x:35)
point = Teoria::NombradoPoint3D.new
pointX.imprimir
pointY.imprimir
pointZ.imprimir
pointXY.imprimir
pointYZ.imprimir
pointXYZ.imprimir
point.imprimir