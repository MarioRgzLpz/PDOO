module Externo
    class A 
    end

    module Interno
        class B
        end
    end
end

module Test
    def test
        puts "Test"
    end
end

class C
    include Test
end

a = Externo::A.new
b = Externo::Interno::B.new
c = C.new
c.test