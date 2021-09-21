ReadMe!! 

Hello Liana. 
My program is at a state where it can read a file (in the main function) 
and convert it to the appropriate dictionary values. It is outputed to the console
Have fun!

Hello person who is going to get this project! My decoder is working and everything should work fine. there are comments to help you understand my code. Let me know if my code is confusing. This is very likely to happen. 


To Mr. Theiss from Liana: 
	I wasn't able to fork Colin's code because the upload was weird. Therefore, I just copied and pasted his code into sublime. 

Alex: Initial run time in milliseconds is around 44 (ranged from 40-50). To optimize, I took out a for loop in decode method and just added one line to each conditional statement in order to make sure the code from the for loop still got into the program. With this one change, the new runs are typically in the mid 30's, sometimes getting closer to 40 or 41 ms. When running the biggest file, txt file 3, there were no errors and some methods already threw IO Exceptions so I didn't add any error handling. 