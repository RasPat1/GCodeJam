class Mural

  def initialize(input_name, output_name)
    @input_name = input_name
    @output_name = output_name
  end

  def call
    input = read_file(@input_name)

    output = File.open(@output_name, "w")
    cases = input.shift
    current_case = 0

    while input.size > 0
      current_case += 1
      mural_length = input.shift
      mural_values = input.shift

      max_beauty = solve(mural_values)
      output.puts "Case ##{current_case}: #{max_beauty}"
    end

    output.close
  end

  def read_file(file_name)
    input = []

    File.open(file_name, 'r') do |f|
      f.each_line do |line|
        input << line
      end
    end

    input
  end

  def solve(mural_values)
    max_beauty = -1
    total_beauty = 0
    half_size = mural_values.size / 2

    mural_values.split('').each_with_index do |char, index|
      num = char.to_i

      old_index = index - half_size
      old_num = mural_values[old_index] if old_index >= 0

      total_beauty += num
      total_beauty -= old_num.to_i if old_num

      max_beauty = total_beauty if total_beauty > max_beauty
    end

    max_beauty
  end
end

input_name = './B-large-practice.in'
output_name = './B-large-practice.out'
Mural.new(input_name, output_name).call