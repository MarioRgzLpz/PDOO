#encoding: UTF-8
module Teoria
    class DefectoPoint3D
        @@MAX = 100
        @@MIN = 0
        def initialize(x=0, y=0, z=0)
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

pointX = Teoria::DefectoPoint3D.new(10)
pointXY = Teoria::DefectoPoint3D.new(-7, 290)
pointXYZ = Teoria::DefectoPoint3D.new(10, 20, 35)
point = Teoria::DefectoPoint3D.new
pointX.imprimir
pointXY.imprimir
pointXYZ.imprimir
point.imprimir