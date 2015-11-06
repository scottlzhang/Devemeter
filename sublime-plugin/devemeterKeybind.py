import sublime, sublime_plugin, urllib

class EnterCommand(sublime_plugin.TextCommand):
    def run(self, edit):
        self.view.insert(edit, self.view.sel()[0].begin(), "\n")
        url='http://localhost:8080/Devemeter/KeyHandler'
        param={"key":"Enter"}
        data=urllib.parse.urlencode(param)
        data=data.encode('utf-8')
        req=urllib.request.Request(url,data)
        with urllib.request.urlopen(req) as response:
            the_page = response.read()
        print(the_page)

class BackspaceCommand(sublime_plugin.TextCommand):
    def run(self,edit):
        print('backspace');
