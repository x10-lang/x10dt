#ifndef __X10_IO_FILEWRITER_H
#define __X10_IO_FILEWRITER_H

#include <x10rt.h>


#define X10_IO_OUTPUTSTREAMWRITER_H_NODEPS
#include <x10/io/OutputStreamWriter.h>
#undef X10_IO_OUTPUTSTREAMWRITER_H_NODEPS
namespace x10 { namespace io { 
class File;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace io { 
class OutputStreamWriter__OutputStream;
} } 
namespace x10 { namespace io { 
class FileWriter__FileOutputStream;
} } 
namespace x10 { namespace io { 

class FileWriter : public x10::io::OutputStreamWriter   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::io::File> FMGL(file);
    
    static x10aux::ref<x10::io::OutputStreamWriter__OutputStream> make(x10aux::ref<x10::lang::String> path);
    void _constructor(x10aux::ref<x10::io::File> file);
    
    static x10aux::ref<x10::io::FileWriter> _make(x10aux::ref<x10::io::File> file);
    
    virtual x10aux::ref<x10::io::FileWriter> x10__io__FileWriter____x10__io__FileWriter__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_IO_FILEWRITER_H

namespace x10 { namespace io { 
class FileWriter;
} } 

#ifndef X10_IO_FILEWRITER_H_NODEPS
#define X10_IO_FILEWRITER_H_NODEPS
#include <x10/io/OutputStreamWriter.h>
#include <x10/io/File.h>
#include <x10/lang/String.h>
#include <x10/io/OutputStreamWriter__OutputStream.h>
#include <x10/io/FileWriter__FileOutputStream.h>
#ifndef X10_IO_FILEWRITER_H_GENERICS
#define X10_IO_FILEWRITER_H_GENERICS
template<class __T> x10aux::ref<__T> x10::io::FileWriter::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::FileWriter> this_ = new (memset(x10aux::alloc<x10::io::FileWriter>(), 0, sizeof(x10::io::FileWriter))) x10::io::FileWriter();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_FILEWRITER_H_GENERICS
#endif // __X10_IO_FILEWRITER_H_NODEPS
