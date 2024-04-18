require_relative 'persona'
require_relative 'cosa'

mochila = Cosa.new("Mochila")
juan = Persona.new("Juan")

juan.otra_cosa(mochila)
juan.saluda

puts juan.to_s