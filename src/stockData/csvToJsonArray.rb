
# read in filepath
file = ARGV[0]

# filename with json extension
fileName = File.basename(file, File.extname(file)) + ".json"

# create a target file which will filename.json
# write in start i.e. ?( \n [
outFile = File.new("#{fileName}", "w")
outFile.puts("?(\n[")

# open txt file (which represents the csv)

# read in line by line adding [ __text__ ],
# for last one don't include ,
# write in closing bit i.e. ]);
csv = File.open("#{file}").readlines
csv.each do |l|
  if l[csv.last]
    outFile.puts("[#{l.chomp}]")
    outFile.puts("]);")
  else
    outFile.puts("[#{l.chomp}],")
  end
end



