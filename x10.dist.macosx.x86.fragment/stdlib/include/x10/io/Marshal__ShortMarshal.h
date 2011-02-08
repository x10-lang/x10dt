#ifndef __X10_IO_MARSHAL__SHORTMARSHAL_H
#define __X10_IO_MARSHAL__SHORTMARSHAL_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_IO_MARSHAL_H_NODEPS
#include <x10/io/Marshal.h>
#undef X10_IO_MARSHAL_H_NODEPS
namespace x10 { namespace lang { 
class Short;
} } 
#include <x10/lang/Short.struct_h>
namespace x10 { namespace io { 
class Reader;
} } 
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace io { 
class Writer;
} } 
namespace x10 { namespace io { 

class Marshal__ShortMarshal : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::io::Marshal<x10_short>::itable<x10::io::Marshal__ShortMarshal > _itable_0;
    
    static x10::lang::Any::itable<x10::io::Marshal__ShortMarshal > _itable_1;
    
    void _instance_init();
    
    virtual x10_short read(x10aux::ref<x10::io::Reader> r);
    virtual void write(x10aux::ref<x10::io::Writer> w, x10_short s);
    virtual x10aux::ref<x10::io::Marshal__ShortMarshal> x10__io__Marshal__ShortMarshal____x10__io__Marshal__ShortMarshal__this(
      );
    void _constructor();
    
    static x10aux::ref<x10::io::Marshal__ShortMarshal> _make();
    
    
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
#endif // X10_IO_MARSHAL__SHORTMARSHAL_H

namespace x10 { namespace io { 
class Marshal__ShortMarshal;
} } 

#ifndef X10_IO_MARSHAL__SHORTMARSHAL_H_NODEPS
#define X10_IO_MARSHAL__SHORTMARSHAL_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/io/Marshal.h>
#include <x10/lang/Short.h>
#include <x10/io/Reader.h>
#include <x10/lang/Byte.h>
#include <x10/lang/Int.h>
#include <x10/io/Writer.h>
#ifndef X10_IO_MARSHAL__SHORTMARSHAL_H_GENERICS
#define X10_IO_MARSHAL__SHORTMARSHAL_H_GENERICS
template<class __T> x10aux::ref<__T> x10::io::Marshal__ShortMarshal::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::io::Marshal__ShortMarshal> this_ = new (memset(x10aux::alloc<x10::io::Marshal__ShortMarshal>(), 0, sizeof(x10::io::Marshal__ShortMarshal))) x10::io::Marshal__ShortMarshal();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_IO_MARSHAL__SHORTMARSHAL_H_GENERICS
#endif // __X10_IO_MARSHAL__SHORTMARSHAL_H_NODEPS
