#encoding: UTF-8
module Teoria
    class Point3D
        @@MAX = 100
        @@MIN = 0
        def initialize(x, y, z)
            @x = restrict(x)
            @y = restrict(y)
            @z = restrict(z)
        end

        def restrict (a)
            result = [@@MIN , a].max
            result = [@@MAX, result].min
            return result
        end

        #Creacion de varios constructores 
        def self.new_X(x)
            new(x, 0, 0)
        end

        def self.new_XY(x, y)
            new(x, y, 0)
        end

        def self.new_XYZ(x, y, z)
            new(x, y, z)
        end

        def self.new_empty
            new(0, 0, 0)
        end

        #Creacion erronea de los constructores. Como el metodo restrict es de instancia y estamos en ámbito de clase no funciona
        def self.new_XY_mal(x, y)
            @x = restrict(x)
            @y = restrict(y)
            @z = 0
        end

        def self.new_XYZ_mal(x, y, z)
            @x = restrict(x)
            @y = restrict(y)
            @z = restrict(z)

        end
        def imprimir
            puts "x: #{@x}, y: #{@y}, z: #{@z}"
        end

        private_class_method :new
    end
end

pointX = Teoria::Point3D.new_X(10)
pointXY = Teoria::Point3D.new_XY(-7, 290)
pointXYZ = Teoria::Point3D.new_XYZ(10, 20, 35)
point = Teoria::Point3D.new_empty
pointX.imprimir
pointXY.imprimir
pointXYZ.imprimir
point.imprimir
pointXYmal = Teoria::Point3D.new_XY_mal(-7, 290)
pointXYZmal = Teoria::Point3D.new_XYZ_mal(10, 20, 35)
pointXYmal.imprimir
pointXYZmal.imprimir
