# read in filepath
file = ARGV[0]

# filename with json extension
fileName = "2" + File.basename(file, File.extname(file)) + ".json"

# create a target file which will be filename2.json
outFile = File.new("#{fileName}", "w")
outFile.puts("?([")

json = File.open("#{file}").readlines
json.each do |l|
  l.chomp!
  if l.start_with?("[2")
    t = l[1,15]
    v = l.split(/,/)[1]
    val = v.split(/]/).first

    year = t[0,4]
    mon = t[4,2]
    day = t[6,2]
    hr = t[7,2]
    min = t[9,2]
    sec = t[11,2]
    
    if l.end_with?(",")
      outFile.puts("[#{Time.new(year,mon,day, hr,min,sec).to_i*1000},#{val}],")
    else
      outFile.puts("[#{Time.new(year,mon,day, hr,min,sec).to_i*1000},#{val}]")
      outFile.puts("]);")
    end 
  end  
end

